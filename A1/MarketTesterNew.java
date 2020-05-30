/**
 * NOTE: THIS TEST IS NOT COMPLETE AND DOES NOT REFLECT YOUR FINAL GRADE FOR THE ASSIGNMENT.
 */
package Assignment1;
public class MarketTesterNew{
    public static void main(String[] args){
        System.out.println("-------Starting Mini Tester--------");
        System.out.println("-------Testing Products------------");
        System.out.println("-------Testing Eggs------------");
        try{
            Egg fancyEggs = new Egg("Fancy Eggs", 4, 380);
            String nameOffancyEggs = fancyEggs.getName();
            if(! nameOffancyEggs.equals("Fancy Eggs")){
                System.out.println("--- FAILED Test for Eggs ----");
                System.out.println("expected:" + "Fancy Eggs");
                System.out.println("Obtained:" + nameOffancyEggs);
            }else{
                System.out.println("---Passed Eggs name test-----");
                //System.out.println(nameOffancyEggs);
            }
            int costOffancyEggs = fancyEggs.getCost();
            if(costOffancyEggs != 126){
                System.out.println("--- FAILED Test for Eggs cost ----");
                System.out.println("expected:" + "126");
                System.out.println("Obtained:" + costOffancyEggs);

            }else{
                System.out.println("---Passed Eggs cost test-----");
                //System.out.println(costOffancyEggs);
            }
        }
        catch(Exception e){
            System.out.println("---FAILED Test for Eggs----");
            stackTrace(e);
        }
        System.out.println("\n\n\n");

        System.out.println("--------Testing Fruit Class--------");
        
        
            Fruit fancyfruit = new Fruit("Fancy fruit", 4, 380);
            String nameOffancyfruit = fancyfruit.getName();
            if(! nameOffancyfruit.equals("Fancy fruit")){
                System.out.println("--- FAILED Test for Eggs ----");
                System.out.println("expected:" + "Fancy Eggs");
                System.out.println("Obtained:" + nameOffancyfruit);
            }else{
                System.out.println("---Passed Eggs name test-----");
                System.out.println(nameOffancyfruit);
            }
            int costOffancyfruit = fancyfruit.getCost();
            if(costOffancyfruit != 1520){
                System.out.println("--- FAILED Test for Eggs cost ----");
                System.out.println("expected:" + "1520");
                System.out.println("Obtained:" + costOffancyfruit);

            }else{
                System.out.println("---Passed Eggs cost test-----");
                System.out.println(costOffancyfruit);
            }
        
        fruitTest();

        
        jamTest();

        
        seasonalFruitTest(); 

        System.out.println("--------Testing Basket Class--------");

        try{
            Basket myBasket = new Basket();
            myBasket.add(new Egg("organic Eggs",5,305));  
            myBasket.add(new Fruit("Blue Berry",1.5,380));
            myBasket.add(new Fruit("Green Berry",1.5,380));
            myBasket.add(new Fruit("red Berry",1.5,380));
            myBasket.add(new Fruit("Random fruit",1.1,340));
            myBasket.add(new Jam("Raspberry Jam", 1, 320));
            myBasket.add(new Jam("Strawberry Jam", 1, 200));
            System.out.println(myBasket.getProducts());
            //System.out.println(myBasket);
            Boolean a = myBasket.remove(new Fruit("Random fruit",1.1,340));
           // System.out.println(myBasket);
            Boolean b = myBasket.remove(new Fruit("Green Berry",5,380));
            if(!a || b){
                System.out.println("--- FAILED Test for Basket remove ----");
                System.out.println("Expected: a=true & b=false");
                System.out.println("Obtained: a=" + a + " & b=" + b);


            }else{
                System.out.println("---Passed test  for Basket remove");
            }
            System.out.println(myBasket);
            System.out.println("numProduct = " + myBasket.getNumOfProducts());
            System.out.println("subtotal = " + myBasket.getSubTotal());
            System.out.println("tax = " + myBasket.getTotalTax());
            System.out.println("total = " + myBasket.getTotalCost());
            myBasket.clear();
//            System.out.println(myBasket);
        }
        catch(Exception e){
            System.out.println("---FAILED Test for Basket ----");
            stackTrace(e);
        }


        System.out.println("--------Testing Customer Class--------");
        try{
            Customer cust  = new Customer("Alice",20000);
            System.out.println(cust.getName());
            System.out.println(cust.getBalance());
            //cust.addFunds(10);
            cust.addFunds(10);
            cust.addToBasket(new Egg("organic Eggs",5,380));
            cust.addToBasket(new Fruit("Blue Berry",1.5,380));
            cust.addToBasket(new Fruit("Red Berry",1.5,38));
            cust.addToBasket(new Fruit("Green Berry",1.5,180));
            cust.addToBasket(new Fruit("Yellow Berry",1.5,40));
            cust.addToBasket(new Jam("Raspberry Jam", 1, 320));
            cust.addToBasket(new Jam("Strawberry Jam", 2, 405));
            // cust.checkOut(); // fails test for --- if run before removing something from basket?
            //System.out.println(cust.removeFromBasket(new Fruit("Yellow Berry",1.5,40)));
            cust.removeFromBasket(new Fruit("Blue Berry",1.5,380));
            //cust.checkOut();
            System.out.println("Balance Before:" + cust.getBalance());
            System.out.println("Basket cost:" + cust.getBasket().getTotalCost());
            System.out.println(cust.checkOut());
            System.out.println("Balance After:" + cust.getBalance());
        }
        catch(IllegalStateException e){
            System.out.println("---Customer did not have enough funds----");
            stackTrace(e);
        }
        catch(IllegalArgumentException e){
            System.out.println("---Illegal amount added to balance----");
            stackTrace(e);
        }

        catch(Exception e){
            System.out.println("---FAILED Test for ----");
            stackTrace(e);
        }

    }

    private static void fruitTest(){
        try{
            Fruit myFruits = new Fruit("Asian Pear",1.25,530);
            int costOfmyFruits = myFruits.getCost();
            if(costOfmyFruits != 662){
                System.out.println("--- FAILED Test for Fruit cost ----");
                System.out.println("expected:" + "662");
                System.out.println("Obtained:" + costOfmyFruits);

            }else{
                System.out.println("---Passed Fruit cost test-----");
            }
        }
        catch(Exception e){
            System.out.println("---FAILED Test for Fruit----");
            stackTrace(e);
        }
    }

    private static void jamTest(){
        try{
            Jam myJam = new Jam("Quince Marmalade",2,475);
            int costOfmyJam = myJam.getCost();
            if(costOfmyJam != 950){
                System.out.println("--- FAILED Test for Jam cost ----");
                System.out.println("expected:" + "950");
                System.out.println("Obtained:" + costOfmyJam);

            }else{
                System.out.println("---Passed Jam cost test-----");
            }
        }
        catch(Exception e){
            System.out.println("---FAILED Test for Jam----");
            stackTrace(e);
        }
    }


    private static void seasonalFruitTest(){
        try{
            SeasonalFruit fancyFruit = new SeasonalFruit("Burzul Walnut", 0.5, 480);
            int costOffancyFruit = fancyFruit.getCost();
            if(costOffancyFruit != 204){
                System.out.println("--- FAILED Test for Seasonal Fruitcost ----");
                System.out.println("expected:" + "204");
                System.out.println("Obtained:" + costOffancyFruit);

            }else{
                System.out.println("---Passed Seasonal cost test-----");
            }
        }
        catch(Exception e){
            System.out.println("---FAILED Test for Seasonal Fruit----");
            stackTrace(e);
        }
    }

    private static void stackTrace(Exception e){
        StackTraceElement[] elements = e.getStackTrace();
        String exception = "";
        for(StackTraceElement s : elements){
            exception += "Class Name: " + s.getClassName() +
                    " Method Name: " +  s.getMethodName() +
                    " Line: " + s.getLineNumber() + "\n";
        }
        System.out.println(exception);
    }

}
