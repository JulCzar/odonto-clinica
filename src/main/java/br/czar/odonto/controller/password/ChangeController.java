package br.czar.odonto.controller.password;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.Request;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.PasswordRecovery;
import br.czar.odonto.model.Patient;
import br.czar.odonto.model.PhysicalPerson;
import br.czar.odonto.model.Security;
import br.czar.odonto.repository.PasswordRecoveryRepository;
import br.czar.odonto.repository.PhysicalPersonRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.time.LocalDateTime;

@Named("changePasswordController")
@ViewScoped
public class ChangeController extends Controller<PasswordRecovery> {
	private String code, email, password, confirmPassword;

	public ChangeController() {
		String code = Request.getQuery("code");
		System.out.println(code);
		setCode(code);
	}

	@Override
	public PasswordRecovery getEntity() {
		if (entity == null)
			entity = new PasswordRecovery();
		return entity;
	}

	public void changePassword() {
		PasswordRecoveryRepository prm = new PasswordRecoveryRepository();
		PhysicalPersonRepository ppr = new PhysicalPersonRepository();

		try {
			PasswordRecovery pr = prm.findByCode(getCode());
			if(!getPassword().equals(getConfirmPassword())) {
				Util.addErrorMessage("As senhas não coincidem");
				return;
			}
			if (!pr.getUser().getEmail().equals(email)) {
				Util.addWarnMessage("O codigo eh invalido para o email informado");
				return;
			}
			if (pr.isUsed()) {
				Util.addErrorMessage("O codigo informado eh invalido");
				return;
			}
			if (pr.getCreated().plusDays(1).isBefore(LocalDateTime.now())) {
				Util.addErrorMessage("O codigo informado expirou");
				return;
			}
			pr.getUser().setPassword(getPassword());
			pr.setUser(Security.encript(pr.getUser()));
			System.out.println(pr.getUser());

			ppr.beginTransaction();
			prm.beginTransaction();

			ppr.save(pr.getUser());
			pr.setUsed(true);
			prm.save(pr);

			ppr.commitTransaction();
			prm.commitTransaction();

			Util.addInfoMessage("Senha Alterada com sucesso");
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
