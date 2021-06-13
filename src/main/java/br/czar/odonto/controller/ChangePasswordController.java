package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.Request;
import br.czar.odonto.model.PasswordRecovery;
import br.czar.odonto.model.PhysicalPerson;
import br.czar.odonto.repository.PasswordRecoveryRepository;
import br.czar.odonto.repository.PhysicalPersonRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.time.LocalDateTime;

@Named
@ViewScoped
public class ChangePasswordController extends Controller<PasswordRecovery> {
	private String code = null;
	private String password = null;

	public ChangePasswordController() {
		String code = Request.getQuery("code");

		if (code != null) this.code = code;
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
			System.out.println(code);
			PasswordRecovery pr = (PasswordRecovery) prm.findByCode(getCode());

			if (pr.isUsed()) {
				Util.addErrorMessage("O codigo informado eh invalido");
				return;
			}

			if (pr.getCreated().plusDays(1).isBefore(LocalDateTime.now())) {
				Util.addErrorMessage("O código informado expirou");
				return;
			}

			PhysicalPerson pp = pr.getUser();

			pp.setPassword(password);

			ppr.save(pp);
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
}
