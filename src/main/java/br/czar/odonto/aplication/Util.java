package br.czar.odonto.aplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;

import com.sun.faces.component.visit.FullVisitContext;

public class Util {

	public static final String PATH_IMAGES = File.separator + "images";
	public static final String PATH_IMAGES_USER = PATH_IMAGES + File.separator + "usuario";
  public Util() {}

	public static void addErrorMessage(String msg) {
		addMessage(null, FacesMessage.SEVERITY_ERROR, msg);
	}
	
	public static void addErrorMessage(String clientId, String msg) {
		addMessage(clientId, FacesMessage.SEVERITY_ERROR, msg);
	}

	public static void addInfoMessage(String msg) {
		addMessage(null, FacesMessage.SEVERITY_INFO, msg);
	}
	
	public static void addWarnMessage(String msg) {
		addMessage(null, FacesMessage.SEVERITY_WARN, msg);
	}
	
	private static void addMessage(String clientId, Severity severity, String msg) {
		FacesContext.getCurrentInstance()
			.addMessage(clientId, new FacesMessage(severity, msg, null));
	}

	public static void redirect(String page) {
		String destination = page;
		if (!page.contains(".xhtml"))
			destination += ".xhtml";
		directRedirect(destination);
	}

	public static void directRedirect(String page) {
		try {
			FacesContext.getCurrentInstance()
				.getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
			addErrorMessage("Problemas ao redirecionar a página.");
		}
	}
	
	public static UIComponent findComponent(final String id) {
    FacesContext context = FacesContext.getCurrentInstance(); 
    UIViewRoot root = context.getViewRoot();
    final UIComponent[] found = new UIComponent[1];

    root.visitTree(new FullVisitContext(context), new VisitCallback() {     
      @Override
      public VisitResult visit(VisitContext context, UIComponent component) {
        if (component != null 
          && component.getId() != null 
          && component.getId().equals(id)) {
          found[0] = component;
          return VisitResult.COMPLETE;
        }
        return VisitResult.ACCEPT;              
      }
    });

    return found[0];
	}

	public static void saveUserAvatar(InputStream inputStream, String imageType, int userId) throws IOException {
		String diretorio = System.getProperty("user.home") + PATH_IMAGES_USER;

		File file = new File(diretorio);
		if (!file.exists()) file.mkdirs();

		BufferedImage bImage = ImageIO.read(inputStream);

		File arquivoFinal = new File(diretorio + File.separator + userId + "." + imageType);
		ImageIO.write(bImage, imageType, arquivoFinal);
	}
}