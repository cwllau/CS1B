// testing InternetUser

public class Foothill_

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