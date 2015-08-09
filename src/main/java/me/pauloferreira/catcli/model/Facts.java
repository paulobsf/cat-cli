package me.pauloferreira.catcli.model;

import java.util.List;

public class Facts {

  private final boolean success;

  private final List<String> facts;

  public Facts(boolean success, List<String> facts) {
    this.success = success;
    this.facts = facts;
  }

  public boolean isSuccess() {
    return success;
  }

  public List<String> getFacts() {
    return facts;
  }
}
