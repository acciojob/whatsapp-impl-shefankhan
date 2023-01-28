package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }
     public String createUser(String name, String mNo) throws Exception{
        if(userMobile.contains(mNo)){
            throw new Exception("User already exist");

        }else {
            User user = new User(name, mNo);
            userMobile.add(mNo);
            return "SUCCESS";
        }
     }
    public Group createGroup(List<User> users){

       // User admin=users.get(0);

        /*if(users.size()==2){
            User otherUser= users.get(1);
             Group personalChat=new Group(otherUser.getName(), users.size());
             adminMap.put(personalChat,admin);
             groupUserMap.put(personalChat,users);
             return personalChat;
        }else{
            String groupName= "Group "+(++customGroupCount);
            Group group= new Group(groupName,users.size());
            adminMap.put(group,admin);
            groupUserMap.put(group,users);
            return group;
        }*/
        if(users.size()>2){
            customGroupCount++;
            Group group = new Group("Group "+customGroupCount, users.size());
            groupUserMap.put(group, users);
            adminMap.put(group, users.get(0));
            groupMessageMap.put(group, new ArrayList<Message>());
            return group;
        }
        Group group = new Group(users.get(1).getName(), users.size());
        groupUserMap.put(group, users);
        adminMap.put(group, users.get(0));
        groupMessageMap.put(group, new ArrayList<Message>());
        return group;




    }
    public int createMessage(String content){
        messageId++;
        Message message=new Message(messageId,content);
        return messageId;
    }

    ///////////////////////////////////
    public int sendMessage(Message message, User sender, Group group) throws Exception{
        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }

        if(!groupUserMap.get(group).contains(sender)){
            throw new Exception("You are not allowed to send message");
        }
        senderMap.put(message,sender);
        List<Message> messages= groupMessageMap.get(group);

        messages.add(message);
        groupMessageMap.put(group,messages);

        return  messages.size();


    }
    public String changeAdmin(User approver, User user, Group group) throws Exception{
        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }
        if(!approver.equals(adminMap.get(group))){
            throw new Exception("Approver does not have rights");
        }
        if(!groupUserMap.get(group).contains(user)){
            throw new Exception("User is not a participant");
        }
        adminMap.put(group,user);
        return "SUCCESS";
    }

}
