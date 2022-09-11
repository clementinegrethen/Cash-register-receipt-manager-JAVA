public class Date {
    
    private int mois;
    private int jour;
    private int annee;
    private  int heure;
    private int minute;
    
    public Date(int mois,int jour, int annee,int heure,int minute) {
      this.mois=mois;
      this.jour=jour;
      this.annee=annee;
      this.heure=heure;
      this.minute=minute;
    }
    
     public int GetMois(){
        return this.mois;
        
    }
     public int GetJour(){
        return this.jour;
    }
     public int GetAnnee(){
        return this.annee;
    }
     public int GetHeure(){
        return this.heure;
    }
     public int GetMinute(){
        return this.minute;
    }
    
    public String ToStringTXT(){
        return "d" + this.jour + this.mois  + this.annee;
    }
    
    public String ToString(){
        return "date:" + this.jour +"/"+ this.mois +"/" + this.annee;
    }
    
    public Date triDate(Date date1, Date date2) {
    	if (date1.annee == date2.annee & date1.mois == date2.mois & date1.jour == date2.jour & date1.heure == date2.heure) { 
    		if (date1.minute > date2.minute) { return date1; }
    		else { return date2; }
    	} else if (date1.annee == date2.annee & date1.mois == date2.mois & date1.jour == date2.jour) {
    		if (date1.heure > date2.heure) { return date1; }
    		else { return date2; }
    	} else if (date1.annee == date2.annee & date1.mois == date2.mois) {
    		if (date1.jour > date2.jour) { return date1; }
    		else { return date2; }
    	} else if (date1.annee == date2.annee) {
    		if (date1.mois > date2.mois) { return date1; }
    		else { return date2; }
    	} else {
    		if (date1.annee > date2.annee) { return date1; }
    		else { return date2; }
    	}
    }
    
    public Boolean egalDate(Date date1, Date date2) {
    	return(date1.annee == date2.annee & date1.mois == date2.mois & date1.jour == date2.jour & date1.heure == date2.heure & date1.minute == date2.minute);
    }
    
}
