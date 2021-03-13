import java.util.*;

class Tweet{
    private int idTw;
    private String username;
    private String msg;
    private TreeSet <String> likes;
    
    public Tweet(int id, String username, String msg){
        this.idTw = id;
        this.username = username;
        this.msg = msg;
        this.likes = new TreeSet<>();
    }
    
    public void like(String username){
        likes.add(username);
    }
    
    public int getIdTw(){
        return idTw;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public String toString(){
        String saida="";
        saida+= idTw + ":" + username + " (" + msg + ") " + likes + "\n";
        return saida;
    }
}

class User{
    private String username;
    private Map <String, User> followers;
    private Map <String, User> following;
    private Map <Integer, Tweet> timeline;
    private int unreadCount;
    
    public User(String username){
        this.following = new TreeMap<>();
        this.followers = new TreeMap<>();
        this.timeline = new TreeMap<>();
        this.username = username;
        this.unreadCount = 0;
    }
  
    public void follow(User user){
        if(following.containsKey(user.getUsername())){
            throw new RuntimeException("você já segue essa pessoa");
        } else {
            following.put(user.getUsername(), this);
            user.followers.put(this.getUsername(), this);
        }
    }   
    
    public void unfollow(User user){
        if(!following.containsKey(user.getUsername())){
            throw new RuntimeException("você não segue essa pessoa");
        } else {
            following.remove(user.getUsername());
            user.followers.remove(this.getUsername());
        }
    }
    
    public void sendTweet(Tweet tweet){
        for(User user : followers.values()){
            user.getUsername();
            user.timeline.put(tweet.getIdTw(), tweet);
            user.unreadCount+=1;
        }
    }
  
    public Tweet getTweet(int idTw){
        Tweet tweet=timeline.get(idTw);
        tweet.like(getUsername());
        return tweet;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getTimeline(){
        String saida="";
        Map <Integer, Tweet> timeline2 = new TreeMap<>();
        for(int i=timeline.size() - this.unreadCount; i < timeline.size(); i++){
            timeline2.put(i, timeline.get(i));   
        }
        if(this.unreadCount==0){
            throw new RuntimeException("não há novos tweets");
        }
        unreadCount = 0;
        for (Tweet tweets : timeline2.values()) {
            saida += tweets;
        }
        return saida;
    }
    
    public String toString(){
        String saida="";
            saida+= getUsername() + "\n" + "   seguidores " + followers.keySet();
            saida+="\n" + "   seguidos " + following.keySet();
        return saida;
    }   
}

public class Sistema {
    private Map <String, User> users;
    private Map <Integer, Tweet> tweets;
    private int nextTwId;
    
    public Sistema(){
        this.users = new TreeMap<>();
        this.tweets = new TreeMap<>();
    }
    
    public void sendTweet(String username, String msg){
        if(!users.containsKey(username)){
            throw new RuntimeException("usuário não existe para fazer um tweet");
        }
        Tweet tweet = new Tweet(nextTwId, username, msg);
        tweets.put(nextTwId, tweet);
        User user = getUser(username);
        user.sendTweet(tweet);
        nextTwId=nextTwId+1;
    }
    
    public void addUser(String username){
        User user = new User(username);
        if(!users.containsKey(username)){
            users.put(username, user);
        }
    }
    
    public User getUser(String username){
        User user = users.get(username);
        if(user == null){
            throw new RuntimeException("usuário não existe");
        } return user;
    }
    
    public String toString(){
        String saida="";
        for(User user : users.values()){
            saida += user + "\n";
        } return saida;    
    }
    
    public static void main(String[] args) {
    Scanner leitor = new Scanner(System.in);
    Sistema sistema = new Sistema();
        while(true){
        String line = leitor.nextLine();
        String ui[] = line.split(" ");
            try {
                if (ui[0].equals("end")) {
                    break;
                } else if (ui[0].equals("adduser")) {
                    sistema.addUser(ui[1]);
                } else if (ui[0].equals("show")) {
                    System.out.print(sistema);
                } else if (ui[0].equals("follow")) {//goku tina
                    User one = sistema.getUser(ui[1]);
                    User two = sistema.getUser(ui[2]);
                    one.follow(two);
                }
                else if (ui[0].equals("twittar")) {//goku msg
                    String username = ui[1];
                    String msg = "";
                    for(int i = 2; i < ui.length; i++)
                        msg += ui[i] + " ";
                    sistema.sendTweet(username, msg);
                }
                else if (ui[0].equals("timeline")) {//goku tina
                    User user = sistema.getUser(ui[1]);
                    System.out.print(user.getTimeline());
                }
                else if (ui[0].equals("like")) {//goku tina
                    User user = sistema.getUser(ui[1]);
                    Tweet tw = user.getTweet(Integer.parseInt(ui[2]));
                    tw.like(ui[1]);
                }else if (ui[0].equals("unfollow")) {//goku tina
                    User user = sistema.getUser(ui[1]);
                    User user2 = sistema.getUser(ui[2]);
                    user.unfollow(user2);
                }else{
                    System.out.println("fail: comando invalido");
                }
            }catch(RuntimeException rt){
                System.out.println(rt.getMessage());
            }
        } leitor.close();
    }
}