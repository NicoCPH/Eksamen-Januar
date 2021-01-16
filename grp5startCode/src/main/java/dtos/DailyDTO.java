/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author GRP 5
 */
public class DailyDTO {

    String boredActivity;
    String boredType;
    String boredParticipants;
    String catMessage;
    String myIP;
    String spaceNumber;
    String trumpMessage;

    public DailyDTO(BoredDTO b, CatFactsDTO c, MyIPDTO m, SpaceDTO s, TrumpQuotesDTO t) {
        this.boredActivity = b.getActivity();
        this.boredType = b.getType();
        this.boredParticipants = b.getParticipants();
        this.myIP = m.getIp();
        this.catMessage = c.getFact();
        this.spaceNumber = s.getNumber();
        this.trumpMessage = t.getMessage();
    }

    public String getBoredActivity() {
        return boredActivity;
    }

    public void setBoredActivity(String boredActivity) {
        this.boredActivity = boredActivity;
    }

    public String getBoredType() {
        return boredType;
    }

    public void setBoredType(String boredType) {
        this.boredType = boredType;
    }

    public String getBoredParticipants() {
        return boredParticipants;
    }

    public void setBoredParticipants(String boredParticipants) {
        this.boredParticipants = boredParticipants;
    }

    public String getCatMessage() {
        return catMessage;
    }

    public void setCatMessage(String catMessage) {
        this.catMessage = catMessage;
    }

    public String getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(String spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public String getTrumpMessage() {
        return trumpMessage;
    }

    public void setTrumpMessage(String trumpMessage) {
        this.trumpMessage = trumpMessage;
    }

}
