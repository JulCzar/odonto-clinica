package br.czar.odonto.controller.profile;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.SessionStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.Allergy;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.model.Patient;
import br.czar.odonto.model.Security;
import br.czar.odonto.repository.DentistRepository;
import br.czar.odonto.repository.PatientRepository;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("adminProfileController")
@ViewScoped
public class AdminController extends Controller<Dentist> {
	@Serial
	private static final long serialVersionUID = 2122937466427799647L;
	private static final String FLASH_KEY = "logged-user";
	private String password, confirmPassword, avatarName;
	private InputStream avatarInputStream;
	private boolean editing;

	public AdminController() {
		setEditing(false);
	}

	@Override
	public Dentist getEntity() {
		if (entity == null) {
			DentistRepository pr = new DentistRepository();
			Dentist p = (Dentist)SessionStorage.getItem(FLASH_KEY);
			try {
				entity = pr.find(p.getId());
				System.out.println(entity);
			} catch (RepositoryException e) {
				entity = new Dentist();
				e.printStackTrace();
			}
		}
		return entity;
	}

	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				avatarInputStream = uploadFile.getInputStream();
				avatarName = uploadFile.getFileName();
				Util.saveUserAvatar(avatarInputStream, "png", getEntity().getId());
			} catch (IOException e) {
				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da imagem deve ser png.");
		}
	}

	public void update() {
		if (avatarInputStream != null) {
			try {
				Util.saveUserAvatar(avatarInputStream, "png", getEntity().getId());
			} catch (IOException e) {
				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
				e.printStackTrace();
			}
		}

		super.store();
		disableEdit();
	}

	public String getAvatarName() {
		return avatarName;
	}
	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}
	public InputStream getAvatarInputStream() {
		return avatarInputStream;
	}
	public void setAvatarInputStream(InputStream avatarInputStream) {
		this.avatarInputStream = avatarInputStream;
	}
	public void changePassword() {
		if (!confirmPassword.equals(password)) Util.addInfoMessage("Senhas não corresponderm");
		if (!confirmPassword.equals(password)) return;

		Dentist p = getEntity();
		p.getPhysicalPerson().setPassword(password);
		p.setPhysicalPerson(Security.encript(p.getPhysicalPerson()));
		Util.addInfoMessage("Sua senha foi alterada!");
		super.store();
	}
	public boolean isEditing() {
		return editing;
	}
	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	public void enableEdit() {
		setEditing(true);
	}
	public void disableEdit() {
		setEditing(false);
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
	public void setConfirmPassword(String password) {
		this.confirmPassword = password;
	}
	public List<String> getAllergies() {
		Object o = SessionStorage.getItem(FLASH_KEY);
		if (o == null) return new ArrayList<>();
		if (o instanceof Patient) return ((Patient)o).getAllergies().stream().map(Allergy::getName).collect(Collectors.toList());
		return new ArrayList<>();
	}
}
