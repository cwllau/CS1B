

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
    
    //check if valid primes
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