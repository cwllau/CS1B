//Assignment 4 - July 18, 2018 - Colleen Lau - CS1B Summer 2018

//This program tests the InternetUser class and Communicator class to ensure that the proper names and ip addresses are able to be stored in that object.
//The communicator class is tested to ensure that the publicKey and privateKey work as documented


public class Foothill

{
    public static void main(String[] args)
    {
        System.out.println("Base Class InternetUser Testing ***********************");
        
        InternetUser iuser1 = new InternetUser();
        
        //constructor making internet user object
        InternetUser iuser2, iuser3, iuser4;
        iuser2 = new InternetUser("potato", "136.2.4.5");
        iuser3 = new InternetUser();
        iuser3.setName("Three");
        // iuser3 = new InternetUser("W", "125.5.8.7");
        // iuser4 = new InternetUser("bad ip", "1.1.1");
        
        System.out.println("Testing constructors: \n");
        System.out.println("User1 is the default user: \n" +iuser1.toString());
        System.out.println("User2 is the user with defined name and ip : \n" +iuser2.toString());
        System.out.println("User3 is the user with defined name : \n" +iuser3.toString());
        System.out.println("-------------------------------\n");
        
        
        //mutator changing the data members
        iuser1.setName("Walle");
        System.out.println("\n Testing Mutators: ");
        System.out.println("User1 after attempted setName to Walle: \n" + iuser1.toString());
        
        iuser1.setName("W");
        System.out.println("User1 after attempted setName to W: \n" + iuser1.toString());
        
        iuser2.setIP("888.8.7.6");
        System.out.println("User2 after attempted setIP to 888.8.7.6: \n" + iuser2.toString());
        
        iuser2.setIP("8.7.6..");
        System.out.println("User2 after attempted setIP to 8.7.6.. \n" + iuser2.toString());
        
        System.out.println("\n\nDerived Class Communicator Testing ***********************\n");
    
        Communicator c1, c2, c3, c4;
        c1 = new Communicator(); //testing default constructor
        c2 = new Communicator(463, 593);
        c3 = new Communicator("Suesuper", "12.12.12.12"); //testing "super" base class constructor
        c4 = new Communicator("Kirby", "98.76.54.32", 1031, 181);
        
        System.out.println("Testing constructors: \n");
        System.out.println("Default constructor\n" + c1.toString());
        System.out.println("constructor that takes in firstPrime and secondPrime parameters\n" + c2.toString());
        System.out.println("constructor using the base class internetuser constructor\n" + c3.toString());
        System.out.println("constructor that takes in firstPrime and secondPrime parameters and uses the base class internetuser constructor\n" +c4.toString());
        
        System.out.println("-------------------------------\n");
        System.out.println("\n Testing Mutators: ");
        
        c1.setName("Mario");
        c2.setIP("808.9.9.9");
        c3.setPrimes(19, 31);
        c4.setPrimes(4, 587);
        
        System.out.println("Testing setName mutator\n" + c1.toString());
        System.out.println("Testing setIP mutator\n" + c2.toString());
        System.out.println("Testing setPrimes mutator\n" + c3.toString());
        System.out.println("Testing setPrimes mutator\n" +c4.toString());
    }
}

//Base Class InternetUser
//InternetUser has a name and ip address. Users can change and create valid names and ip addresses for their InternetUser

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


//The communicator is derived from the InternetUser however with the communicator, you are able to have publicKey and privateKey. Our program validates publicKey and privateKey.
class Communicator extends InternetUser
{
    public static final int ERROR_FLAG_NUM = 0;
    private static final long MAX_PQ = (long) Math.sqrt(Long.MAX_VALUE);
    
    private IntPair publicKey;
    private IntPair privateKey;
    private long firstPrime; //p = firstPrime
    private long secondPrime; //q = secondPrime
    private long n, phi, e, d;

    //constructors
    //default constructor
    public Communicator() 
    {
        super(); //so we can access InternetUser methods later
        setDefault(ERROR_FLAG_NUM);
    }
    
    //constructor that takes in firstPrime and secondPrime parameters
   public Communicator(long firstPrime, long secondPrime)
   {
      if( !setPrimes(firstPrime, secondPrime) )
      {
         setDefault(ERROR_FLAG_NUM);
      }
   }
   
   //constructor using the base class internetuser constructor 
   public Communicator(String name, String ip)
   {
      super(name, ip);
      setDefault(ERROR_FLAG_NUM);
   }
   
   //constructor that takes in firstPrime and secondPrime parameters and uses the base class internetuser constructor
   public Communicator(String name, String ip, long firstPrime, long secondPrime)
   {  
      super(name, ip);
      if( !setPrimes(firstPrime, secondPrime) )
      {
         setDefault(ERROR_FLAG_NUM);
      }
   }
    
    
    //sets all values to zero
    private void setDefault(int value) 
    {
        publicKey = new IntPair();
        privateKey = new IntPair();
        firstPrime = value;
        secondPrime = value;
        phi = e = n = d = value;
        
    }
    
    //check if valid primess
    private static boolean ValidPrime(long p, long q)
    {
        if (EncryptionSupport.isPrime(p) && EncryptionSupport.isPrime(q) && (p <= MAX_PQ) && (q <= MAX_PQ) )
        {
            return true;
        }
        return false;
    }
    
    // mutator
   public boolean setPrimes(long firstPrime, long secondPrime)
   {
      if( ValidPrime(firstPrime, secondPrime) )
      {
         this.firstPrime = firstPrime;
         this.secondPrime = secondPrime;
         computeBothEncrKeys(firstPrime, secondPrime);
         return true;
      } return false;
   }
   
   private boolean computeBothEncrKeys(long p, long q)
   {
      if ( !ValidPrime(p,q) )
      {
         return false;
      } 
         n = p * q;
         phi = (p-1)*(q-1);
         int attempts = 0;
         
         while(e > phi || e <= 0 || phi % e == 0 && attempts < 10)
         {
            e = EncryptionSupport.getSmallRandomPrime();
            attempts++;
         }
         if( e > phi || e <= 0 || phi % e == 0)
         {
            e = ERROR_FLAG_NUM;
            return false;
         }
         
         d = EncryptionSupport.inverseModN(e, n);
         publicKey = new IntPair(e, n);
         privateKey = new IntPair(d, n);
         return true;
   }
   
   // returns a communicator string with all members 
   public String toString()
   {
      String str = super.toString();
      
      str += "(p, q) n, phi, e, d: (" + firstPrime + ", " + secondPrime + ")   " + n + ", " + phi + ", " + e + ", " + d +"\n";
      str += "public key: " + publicKey.toString() + "\n" + "private key: " + privateKey.toString() + "\n";
      return str;
   }
    
    //accessors
    IntPair getPublicKey()
   {
      return publicKey;
   }
   
   IntPair getPrivateKey()
   {
      return privateKey;
   }
    
    
}


//---------------CONSOLE OUTPUT----------------
/*
Base Class InternetUser Testing ***********************
Testing constructors: 

User1 is the default user: 
Name: (undefined)
IP Address: 0.0.0.0

User2 is the user with defined name and ip : 
Name: potato
IP Address: 136.2.4.5

User3 is the user with defined name : 
Name: Three
IP Address: 0.0.0.0

-------------------------------


 Testing Mutators: 
User1 after attempted setName to Walle: 
Name: Walle
IP Address: 0.0.0.0

User1 after attempted setName to W: 
Name: Walle
IP Address: 0.0.0.0

User2 after attempted setIP to 888.8.7.6: 
Name: potato
IP Address: 888.8.7.6

User2 after attempted setIP to 8.7.6.. 
Name: potato
IP Address: 8.7.6..



Derived Class Communicator Testing ***********************

Testing constructors: 

Default constructor
Name: (undefined)
IP Address: 0.0.0.0
(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)

constructor that takes in firstPrime and secondPrime parameters
Name: (undefined)
IP Address: 0.0.0.0
(p, q) n, phi, e, d: (463, 593)   274559, 273504, 443, 119616
public key: (443, 274559)
private key: (119616, 274559)

constructor using the base class internetuser constructor
Name: Suesuper
IP Address: 12.12.12.12
(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)

constructor that takes in firstPrime and secondPrime parameters and uses the base class internetuser constructor
Name: Kirby
IP Address: 98.76.54.32
(p, q) n, phi, e, d: (1031, 181)   186611, 185400, 197, 118408
public key: (197, 186611)
private key: (118408, 186611)

-------------------------------


 Testing Mutators: 
Testing setName mutator
Name: Mario
IP Address: 0.0.0.0
(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)

Testing setIP mutator
Name: (undefined)
IP Address: 808.9.9.9
(p, q) n, phi, e, d: (463, 593)   274559, 273504, 443, 119616
public key: (443, 274559)
private key: (119616, 274559)

Testing setPrimes mutator
Name: Suesuper
IP Address: 12.12.12.12
(p, q) n, phi, e, d: (19, 31)   589, 540, 79, 507
public key: (79, 589)
private key: (507, 589)

Testing setPrimes mutator
Name: Kirby
IP Address: 98.76.54.32
(p, q) n, phi, e, d: (1031, 181)   186611, 185400, 197, 118408
public key: (197, 186611)
private key: (118408, 186611)
*/