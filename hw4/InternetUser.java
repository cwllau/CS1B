//Base Class InternetUser
//Internet User has a name and ip address

class InternetUser
{
    //csts
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MIN_IP_LENGTH = 7;
    public static final int MAX_IP_LENGTH = 15;
    public static final String DEFAULT_IP = "0.0.0.0";
    public static final String DEFAULT_NAME = "(undefined)";
    
    private String name;
    private String ip;
    
    //default constructor
    public InternetUser()
    {
        name = DEFAULT_NAME;
        ip = DEFAULT_IP;
    }
    
    //constructor with 2 parameters; string name and ip
    public InternetUser (String name, String ip)
    {
        if (!setName(name))
        {
            this.name = DEFAULT_NAME;
        }
        if (!setIP(ip))
        {
            this.ip = DEFAULT_IP;
        }
    }
    
    //mutator name
    public boolean setName(String newName)
    {
        if(isValidName(newName))
        {
            name = newName;
            return true;
        }
        return false; //not valid name
    }
    
    //test if name is valid length
    private boolean isValidName(String newName)
    {
        if (newName.length() <MIN_NAME_LENGTH || newName.length()> MAX_NAME_LENGTH)
        {
            return false;
        }
        else
            return true;
    }
    
    //accessor
    public String getName()
    {
        return name;
    }
    
    //mutator ip
    public boolean setIP(String newIP)
    {
        if (isValidIP(newIP))
        {
            ip = newIP; //valid IP
            return true;
        }
        else
            return false;
    }
    
    //test if ip address is valid length
    private boolean isValidIP(String newIP)
    {
        if (newIP.length() < MIN_IP_LENGTH || newIP.length() > MAX_IP_LENGTH)
        {
            return false;
        }
        else
            return true;
    }
    
    //accessor
    public String getIP()
    {
        return ip;
    }
    
    public String toString()
    {
        String string_name = "Name: " + name + "\n";
        String string_ip = "IP Address: " + ip + "\n";
        return string_name + string_ip;
    }
    
}