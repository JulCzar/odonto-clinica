package br.czar.odonto.controller;

import br.czar.odonto.aplication.MailSender;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.PasswordRecovery;
import br.czar.odonto.model.PhysicalPerson;
import br.czar.odonto.repository.PhysicalPersonRepository;
import br.czar.odonto.repository.Repository;
import jakarta.mail.MessagingException;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.text.DecimalFormat;

@Named("recoveryController")
@ViewScoped
public class PasswordRecoveryController extends Controller<PasswordRecovery> {
	MailSender mailSender = null;
	private String email;

	public PasswordRecoveryController() {
		this.mailSender = new MailSender();
	}

	@Override
	public PasswordRecovery getEntity() {
		if (entity == null)
			entity = new PasswordRecovery();

		return entity;
	}

	private String createCode() {
		double c1 = Math.floor(Math.random() * 1000);
		double c2 = Math.floor(Math.random() * 1000);

		DecimalFormat format = new DecimalFormat("0000");
		String code = format.format(c1)+"-"+format.format(c2);

		return code;
	}

	public void recoverPassword() {
		PhysicalPersonRepository repo = new PhysicalPersonRepository();
		PhysicalPerson p = null;
		try {
			p = repo.findByEmail(getEmail());

			String code = createCode();

			PasswordRecovery ps = getEntity();
			ps.setCode(code);
			ps.setUser(p);
			ps.setUsed(false);

			try {
				storeRecoveryPassword();
				mailSender.sendMail("Seu codigo de recuperacao é: "
					+ "<br/>Você também pode clicar no link: http://localhost:8080/senha/recuperar.xhtml?code=" + code, getEmail(), "Password Recovery");
				Util.addInfoMessage("Foi enviado um email com instruções para recuperação de senha");
			} catch (MessagingException e) {
				e.printStackTrace();
				Util.addWarnMessage("Houve um erro ao enviar a mensagem!");
			} catch(RepositoryException e) {
				e.printStackTrace();
			}
 		} catch (NoResultException e) {
			e.printStackTrace();
			Util.addWarnMessage("Não foi encontrado conta relacionada a esse email no sistema!");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addWarnMessage("Houve um erro ao processar a requisição!");
		}
	}

	public void storeRecoveryPassword() throws RepositoryException {
		Repository<PasswordRecovery> repo = new Repository<>();

		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();

			clear();
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			throw e;
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
