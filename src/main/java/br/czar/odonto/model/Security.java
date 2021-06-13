package br.czar.odonto.model;

import br.czar.odonto.aplication.Util;
import org.apache.commons.codec.digest.DigestUtils;

public class Security {
	private Security() {}
	public static PhysicalPerson encript(PhysicalPerson p) {
		String last3 = p.getPassword().substring(p.getPassword().length() - 3);
		String encripted = DigestUtils.sha256Hex(p.getPassword() + last3);
		p.setPassword(encripted);

		return p;
	}
}