import java.util.*;
import java.io.*;
public class TestSoccer
{
  public static void main(String[] args)throws IOException{
    Scanner in=new Scanner(new File(args[0]));
    Scanner kb=new Scanner(System.in);
    int leng=in.nextInt();
    SoccerPlayer[] players=new SoccerPlayer[leng];
    in.nextLine();
    for(int i=0;i<leng;i++){
      String line=in.nextLine();
      String[] parts=line.split(",");
      SoccerPlayer dude=new SoccerPlayer(parts[0],Integer.parseInt(parts[1]),parts[2],parts[3],Integer.parseInt(parts[4]));
      players[i]=dude;
    }
    System.out.print("Enter a Team: ");
    String testTeam=kb.next();
    ArrayList<SoccerPlayer> tea=new ArrayList<SoccerPlayer>();
    tea=team(players,testTeam);
    if(tea==null){
      System.out.print("Not Enough Players!");
    }else{
      for(int i=0;i<tea.size();i++){
        System.out.print(tea.get(i));
      }
    }
  }
  public static ArrayList<SoccerPlayer> team(SoccerPlayer[] p,String s){
    ArrayList<SoccerPlayer> play=new ArrayList<SoccerPlayer>();
    int defLim=0;
    int atkLim=0;
    int kepLim=0;
    int midLim=0;
    for(int i=0;i<p.length;i++){
      String s1=p[i].getTeam();
      if(s1.equals(s)){
        String s2=p[i].getPosition();
        if(s2.equals("attacker")&&atkLim<3){
          play.add(p[i]);
          atkLim++;
        }else if(s2.equals("defender")&&defLim<4){
          play.add(p[i]);
          defLim++;
        }else if(s2.equals("keeper")&&kepLim<1){
          play.add(p[i]);
          kepLim++;
        }else if(s2.equals("midfielder")&&midLim<3){
          play.add(p[i]);
          midLim++;
        }
      }
    }
    if(defLim==4&&atkLim==3&&kepLim==1&&midLim==3){
      return play;
    }else{
      return null;
    }
  }
}
class SoccerPlayer
{
  private String lastname;
  private int jersey;
  private String position;
  private String team;
  private boolean captain;
  private int id;
  static int nextId=10000;
  private String trueCaptain;
  
  public SoccerPlayer(){
    lastname="";
    jersey=0;
    position="";
    team="";
    captain=false;
    id=nextId;
    nextId++;
  }
  public SoccerPlayer(String l, int j, String p, String t, int c){
    lastname=l;
    jersey=j;
    position=p.toLowerCase();
    team=t;
    if(c==0){
      captain=false;
    }else{
      captain=true;
    }
    id=nextId;
    nextId++;
  }
  public String toString(){
    if(captain==true){
      trueCaptain="Yes";
    }else{
      trueCaptain="No";
    }
    return "\n----------------\nId: "+id+"\nName: "+lastname+"\nJersey#: "+jersey+"\nPosition: "+position+"\nTeam: "+team+"\nCaptain: "+trueCaptain+"\n----------------";
  }
  public String getName(){
    return lastname;
  }
  public int getJersey(){
    return jersey;
  }
  public String getPosition(){
    return position;
  }
  public String getTeam(){
    return team;
  }
  public boolean getCaptain(){
    return captain;
  }
  public int getId(){
    return id;
  }
  public void setName(String n){
    lastname=n;
  }
  public void setJersey(int j){
    jersey=j;
  }
  public void setPosition(String p){
    position=p;
  }
  public void setTeam(String t){
    team=t;
  }
  public void setCaptain(int c){
    if(c==0){
      captain=false;
    }else{
      captain=true;
    }
  }
}