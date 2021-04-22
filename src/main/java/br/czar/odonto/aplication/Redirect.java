package br.czar.odonto.aplication;

import javax.inject.Named;

@Named
public class Redirect {
  public void to(String page) {
    Util.redirect(page);
  }
}
