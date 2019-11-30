import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.text.BadLocationException;

public class VMS extends JFrame
{
	static JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11; 
	static JButton b1,b2,b3,clear;
	Container con;
	static JTextField t1,t2,t3,t4;
	JTextArea ta1;
	JComboBox jc;
	Vector v;
    static String name,loc;
    static int ch;
    private PrintStream standardOut;
    public static int maincounter=1;

         public static int cacheSizelru = 5,newcacheSizelru=0;
         public static int cacheSizefifo = 5,newcacheSizefifo=0;
         public static int cacheSizeopt = 5,newcacheSizeopt=0;



         

	VMS()
    {
		super("Virtual Memory Simulator");
        ImagePanel panel = new ImagePanel(new ImageIcon("blog2.jpg").getImage());

       	ta1=new JTextArea();
        ta1.setBounds(625,400,400,200);
        ta1.setEditable(true);

        PrintStream printStream = new PrintStream(new CustomOutputStream(ta1));
         
        JScrollPane scroll = new JScrollPane(ta1);
        setPreferredSize(new Dimension(450, 110));
       
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         add(scroll, BorderLayout.CENTER);

        
        // keeps reference of standard output stream
        standardOut = System.out;
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
        con = new Container();
       
 		con=getContentPane();
        con.setBackground(Color.lightGray);
		con.setLayout(null);
        con.setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //con.add(panel);
        con.add(ta1);

		l1=new JLabel("Menu:");
		con.add(l1);
		l1.setBounds(625, 10, 200, 40);

		l2=new JLabel("1.Initialize Cache");
		con.add(l2);
		l2.setBounds(625, 40 , 200, 40);


		l3=new JLabel("2.See Cache Status");
		con.add(l3);
		l3.setBounds(625,70 , 200, 40);


		l4=new JLabel("3.Search for shop location");
		con.add(l4);
		l4.setBounds(625, 100, 200, 40);

        l6=new JLabel("4.Display initial Database");
		con.add(l6);
		l6.setBounds(625, 130, 200, 40);  


		l5=new JLabel("5.Exit");
		con.add(l5);
		l5.setBounds(625,160 , 200, 40);

        l11=new JLabel("Enter Choice");
		con.add(l11);
		l11.setBounds(625,225, 100, 30);



        t2=new JTextField();
        con.add(t2);
        t2.setBounds(725,225,150,30);



        b2=new JButton("OK");
      
        b2.setBounds(900,225,80,30);
        con.add(b2);
        l7=new JLabel("Name:");
        con.add(l7);
        l7.setBounds(625,275,100,30);


         t3=new JTextField();
        con.add(t3);
        t3.setBounds(725,275,150,30);



         l8=new JLabel("Location:");
        con.add(l8);
        l8.setBounds(625,325,100,30);


        t4=new JTextField();
        con.add(t4);
        t4.setBounds(725,325,150,30);
        clear = new JButton("Clear");
        con.add(clear);
        clear.setBounds(625,650,100,30);
        clear.addActionListener(new java.awt.event.ActionListener()
         {
           
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                // clears the text area
                try 
                {
                    ta1.getDocument().remove(0,ta1.getDocument().getLength());
                    standardOut.println("Text area cleared");
                } 
                catch (BadLocationException ex) 
                {
                    ex.printStackTrace();
                }
            }
        });

        b3=new JButton("Enter");
        con.add(b3);
        b3.setBounds(900,275,80,30);
        

        l9=new JLabel("Output:");
        con.add(l9);
        l9.setBounds(625,375,100,30);
        this.setSize(900,800);
        this.setVisible(true);
        con.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	public static void main(String[] args) throws Exception 
    {
        VMS v1 = new VMS();
	SwingUtilities.invokeLater(new Runnable() 
    {
        
            public void run()
            {
                //VMS v1 = new VMS();
                v1.setVisible(true);
                //new VMS().setVisible(true);
            }
   });
       
        LRUCache cache = new LRUCache(cacheSizelru);
        FIFO cache1 = new FIFO(cacheSizefifo);
        Optimal cache2 = new Optimal(cacheSizeopt);
      

            if(VMS.maincounter%5==0)
            {
            VMS.newcacheSizelru=((cache1.fifmisscounter+cache2.optmisscounter)/(cache.lrumisscounter+cache1.fifmisscounter+cache2.optmisscounter+1))*15;
            VMS.newcacheSizefifo=((cache.lrumisscounter+cache2.optmisscounter)/(cache.lrumisscounter+cache1.fifmisscounter+cache2.optmisscounter+1))*15;
            VMS.newcacheSizeopt=((cache.lrumisscounter+cache1.fifmisscounter)/(cache.lrumisscounter+cache1.fifmisscounter+cache2.optmisscounter+1))*15;
            cache.resize_lru(cache.cacheSize,VMS.newcacheSizelru);
            cache1.resize_fifo(cache1.cacheSize,VMS.newcacheSizefifo);
            cache2.resize_lru(cache2.cacheSize,VMS.newcacheSizeopt);
            
            }





            b2.addActionListener(new java.awt.event.ActionListener()
         	{
         	public void actionPerformed(java.awt.event.ActionEvent e) 
         	{   
         		String data = t2.getText();
            	ch=Integer.parseInt(data);
                VMS.maincounter++;
            switch(ch)
            {

            case 1: 
            System.out.println("*****Initialize Cache*******"); 
           
             
            
            System.out.println("enter 5 values"); 

              int m=0;
           
            b3.addActionListener(new java.awt.event.ActionListener() 
        	{
         	public void actionPerformed(java.awt.event.ActionEvent e) 
        	{  
                loc = t4.getText();
            	name = t3.getText();
            	cache.initialize_cache(name,loc);
                cache1.initialize_cache(name,loc);
                cache2.initialize_cache(name,loc);


            }

            });

            
            
            System.out.println("LRU Cache:");
            cache.printCacheState();
            System.out.println("FIFO Cache:");
            cache1.printCacheState();
            System.out.println("Optimal Cache:");
            cache2.printCacheState();
         

            break;

            case 2: System.out.println("*****Cache Status*******");
           
            
            System.out.println("The LRU Cache Status is:\n LRU hits"+cache.lruhitcounter+"\n LRU misses"+cache.lrumisscounter);
            System.out.println("The FIFO Cache Status is:\n FIFO hits"+cache1.fifohitcounter+"\n FIFO misses"+cache1.fifmisscounter);
            System.out.println("The Optimal Cache Status is:\n Optimal hits"+cache2.opthitcounter+"\n Optimal misses"+cache2.optmisscounter);   
            System.out.println("LRU Cache:");
            cache.printCacheState();
            System.out.println("FIFO Cache:");
            cache1.printCacheState();
            System.out.println("Optimal Cache:");
            cache2.printCacheState();
        
            break; 
            

            case 3:System.out.println("Enter the shop name whose location is to be searched");
          
          
            int d=0;
            
    		b3.addActionListener(new java.awt.event.ActionListener() 
        	{
         		public void actionPerformed(java.awt.event.ActionEvent e) 
        		{     
           
            		name = t3.getText();
                    VMS.loc = t4.getText();
            		
            System.out.println("LRU:");
            cache.accessPage(v1.name);
            System.out.println("FIFO:");
            cache1.accessPage(v1.name);
            System.out.println("Optimal:");
            cache2.accessPage(v1.name);
            }

            }); 

            break;

            case 4:
        
            //System.out.println("LRU:" +cache.cacheSize);
            HashDB hm3 = new HashDB();
            hm3.display();
            //System.out.println("FIFO:"+cache1.cacheSize);
        
            //System.out.println("Optimal:"+cache2.cacheSize);
    		break;

            case 5: System.exit(0);
           
            break;
    
            default: System.out.println("Invalid Choice");
        }


     }
    });
}




}

class CustomOutputStream extends OutputStream 
{
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) 
    {
        this.textArea = textArea;
    }
     
   
    public void write(int b) throws IOException 
    {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}


class LRUCache 
    {
        
        private DoublyLinkedList pageList;
        int cacheSize;
        HashDB hm1 = new HashDB();
        int lruhitcounter=0;
        int lrumisscounter=0;
        public LRUCache(int cacheSize) 
        {
                this.cacheSize = cacheSize;
                pageList = new DoublyLinkedList(cacheSize);
                
        }
        public void initialize_cache(String pageNumber, String value) 
            {
                pageList.addPageToList_head(pageNumber, value);
                lrumisscounter ++;
            }
        public void accessPage(String pageNumber) 
        {
                    Node pageNode = null;
                    if(pageList.pageListcontains_check_only(pageNumber)) 
                    {
                  
                        lruhitcounter++;
                        System.out.println("LRU Cache Hit");
                        pageNode =pageList.pageListcontains(pageNumber) ;
                        pageList.movePageTohead(pageNode);
                        } 
                        else 
                        {

                            System.out.println("LRU Cache Miss");

                            lrumisscounter++;
                            if(pageList.getCurrSize() < pageList.getSize() && hm1.search(pageNumber)) 
                            {
                    
                             pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                                        
                            }
                            else if(pageList.getCurrSize() == pageList.getSize() && hm1.search(pageNumber)) 
                            {
                        
                            pageList.removepage_tail();
                        
                            pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                                        
                            }

                            else if(pageList.getCurrSize() < pageList.getSize() && !hm1.search(pageNumber))
                            {

                            //Scanner sc=new Scanner(System.in);      

                            System.out.println("Not present in database - Performing Write Through");
                            System.out.println("Enter Value");
                            String value=VMS.loc;
                            hm1.putindb(pageNumber,value);
                            pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());

                            }
                            else if(pageList.getCurrSize() == pageList.getSize() && !hm1.search(pageNumber))
                            {
                            pageList.removepage_tail();

                            //Scanner sc=new Scanner(System.in);      

                            System.out.println("Not present in database - Performing Write Through");
                            System.out.println("Enter Value");
                            String value=VMS.loc;
                            hm1.putindb(pageNumber,value);
                            pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());


                            }
                            else
                            {
              
                            }
                        }

        }
        
        public void printCacheState() 
        {
            System.out.println("Name     Location");
            pageList.printList();
        }

        public void resize_lru(int size,int newSize)
                {
                    this.cacheSize=size;
                    pageList.resize_lru(size,newSize);
                }
                
} 

class Optimal 
    {
        
        private DoublyLinkedList pageList;
        int cacheSize;
        HashDB hm1 = new HashDB();
        int opthitcounter=0;
        int optmisscounter=0;
        public Optimal(int cacheSize) 
        {
                this.cacheSize = cacheSize;
                pageList = new DoublyLinkedList(cacheSize);
                
        }
        public void initialize_cache(String pageNumber, String value) 
        {
                pageList.addPageToList_head(pageNumber, value);
                optmisscounter++;

        }
        public void accessPage(String pageNumber) 
        {
                Node pageNode = null;

                if(pageList.pageListcontains_check_only(pageNumber)) 
                {
                   
                    opthitcounter++;
                    System.out.println("Optimal Cache Hit");
                    pageNode =pageList.pageListcontains(pageNumber) ;
                    pageList.movePageTohead(pageNode);
                } 
                        
                else 
                {

                    System.out.println("Optimal Cache Miss");
                    optmisscounter++;

                    if(pageList.getCurrSize() < pageList.getSize() && hm1.search(pageNumber)) 
                    {
                    
                        pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                                        
                    }
                            
                    else if(pageList.getCurrSize() == pageList.getSize() && hm1.search(pageNumber)) 
                    {
                        
                        pageList.removepage_cmp();
                        pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                    }

                    else if(pageList.getCurrSize() < pageList.getSize() && !hm1.search(pageNumber))
                    {
                        //Scanner sc=new Scanner(System.in);      
                        System.out.println("Not present in database - Performing Write Through");
                        System.out.println("Enter Value");
                        //String value=sc.nextLine();
                        String value= VMS.loc;
                        hm1.putindb(pageNumber,value);
                        pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                        hm1.display();
                    }

                    else if(pageList.getCurrSize() == pageList.getSize() && !hm1.search(pageNumber))
                    {
                        pageList.removepage_cmp();
                        //Scanner sc=new Scanner(System.in);      
                        System.out.println("Not present in database - Performing Write Through");
                        System.out.println("Enter Value");
                        String value=VMS.loc;
                        hm1.putindb(pageNumber,value);
                        pageList.addPageToList_head(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                        hm1.display();
                    }

                    else{}
                }

        }
        
        public void printCacheState() 
        {
            System.out.println("Name     Location");
            pageList.printList();
            //System.out.println("\n");
        }

        public void resize_lru(int size,int newSize)
                {
                    this.cacheSize=size;
                    pageList.resize_lru(size,newSize);
                }
                
 }
class FIFO 
    {
        
        private DoublyLinkedList pageList;
         int cacheSize;
         int fifohitcounter=0;
         int fifmisscounter=0;
         HashDB hm1=new HashDB();
            public FIFO(int cacheSize) 
            {
            this.cacheSize = cacheSize;
            pageList = new DoublyLinkedList(cacheSize);
         
            }
            public void initialize_cache(String pageNumber, String value) 
            {
                pageList.addPageToList_tail(pageNumber, value);
                fifmisscounter++;

            }

			public void accessPage(String pageNumber) 
            {
                Node pageNode = null;
                if(pageList.pageListcontains_check_only(pageNumber)) 
                {
                   
                    System.out.println("FIFO Cache Hit");
                    fifohitcounter++;
                }
                else
                {

                    System.out.println("FIFO Cache Miss");
                    fifmisscounter++;
                        if(pageList.getCurrSize() < pageList.getSize() && hm1.search(pageNumber)) 
                    {
                        
                        pageList.addPageToList_tail(pageNumber, hm1.loadfromdb(pageNumber).getValue());  //rear like a queue
                                        
                    }
                    else if(pageList.getCurrSize() == pageList.getSize() && hm1.search(pageNumber)) 
                    {
                        
                        pageList.removepage_head();
                        
                        pageList.addPageToList_tail(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                                        
                    }

                    else if(pageList.getCurrSize() < pageList.getSize() && !hm1.search(pageNumber))
                    {
                                
                        
                        Scanner sc=new Scanner(System.in);  
                        System.out.println("Not present in database - Performing Write Through");
                        System.out.println("Enter Value");
                        //String value=sc.nextLine();
                        String value=VMS.loc;
                        hm1.putindb(pageNumber,value);
                        //hm1.display();
                        pageList.addPageToList_tail(pageNumber, hm1.loadfromdb(pageNumber).getValue());
                    }
                    else if(pageList.getCurrSize() == pageList.getSize() && !hm1.search(pageNumber))
                    {
                        Scanner sc=new Scanner(System.in);
                        pageList.removepage_head();
                        System.out.println("Not present in database - Performing Write Through");
                        System.out.println("Enter Value");
                        //String value=sc.nextLine();
                        String value=VMS.loc;
                        hm1.putindb(pageNumber,value);
                        //hm1.display();
                        pageList.addPageToList_tail(pageNumber, hm1.loadfromdb(pageNumber).getValue());

                    }
                    else
                    {
              
                    }

                   }
			}
        
        public void printCacheState() 
        {   
        System.out.println();   
        System.out.println("Name     Location");
            pageList.printList();
            //System.out.println("\n");
        }

        public void resize_fifo(int size,int newSize)
                {
                    this.cacheSize=size;
                    pageList.resize_fifo(size,newSize);
                }
}           

class DoublyLinkedList 
    {
         int size;
         int currSize;
        Node head=null;
        Node tail=null;
    
        public DoublyLinkedList(int size) 
        {
            this.size = size;
            this.currSize = 0;
        }   
        public Node gettail() 
        {
            return this.tail;
        }
    
        public void printList() 
        {
            if(this.head == null)
            {
                return;
            }

            Node tmp = this.head;
            while(tmp != this.tail) 
            {
                System.out.print(tmp);
                tmp = tmp.getNext();
                System.out.println("\n");
            }
        }
    
        public Node addPageToList_head(String pageNumber, String value) 
        {
            //this.currSize++;
            Node pageNode = new Node(pageNumber, value);        
            if(this.head == null) 
            {
                this.head = pageNode;
                this.tail = pageNode; 
                this.currSize = 1;
                return pageNode;
            } 
            else if(this.currSize < this.size) 
            {
                this.currSize++;
            } 
            else if(this.currSize == this.size)   //removed tail
            {
                this.tail = this.tail.getPrev();
                this.tail.setNext(this.head);
                this.head.setPrev(this.tail);
            }
            else
            {}

            pageNode.setNext(this.head);
            this.head.setPrev(pageNode);
            this.tail.setNext(pageNode);
            this.head = pageNode;
            this.head.setPrev(this.tail);
            return pageNode;
        }
        public Node addPageToList_tail(String pageNumber, String value) 
        {
            //this.currSize++;
            Node pageNode = new Node(pageNumber, value);        
            if(this.head == null) 
            {
                this.head = pageNode;
                this.tail = pageNode; 
                this.currSize = 1;
                return pageNode;
            } 
            else if(this.currSize < this.size) 
            {
                this.currSize++;
            } 
            else if(this.currSize==this.size)   //remove head
            {
                this.head = this.head.getNext();
                this.head.setPrev(this.tail);
                this.tail.setNext(this.head);
            }
            else
            {}
            pageNode.setPrev(this.tail);
            Node prev=this.tail.getPrev();
            this.tail.setNext(pageNode);
            pageNode.setNext(this.head);
            
            this.head.setPrev(pageNode);
            this.tail=pageNode;


            return pageNode;
        }

        public void movePageTohead(Node pageNode)
        {
            if(pageNode == null || pageNode == this.head) 
            {
                return;
            }
            if(pageNode == this.tail) 
            {
                this.tail = this.tail.getPrev();
                this.tail.setNext(null);
            }

            Node prev = pageNode.getPrev();
            Node next = pageNode.getNext();
            prev.setNext(next);
            if(next != null) 
            {
                next.setPrev(prev);
            }
            pageNode.setPrev(null);
            pageNode.setNext(this.head);
            this.head.setPrev(pageNode);
            this.head = pageNode;    
        }
        public int getCurrSize() 
        {
            return this.currSize;
        }
    
        public void setCurrSize(int currSize) 
        {
            this.currSize = currSize;
        }
    
        
        public int getSize() 
        {
            return this.size;
        }


        boolean pageListcontains_check_only(String name) 
        {
            if(this.head == null)
            {
                return false;
            }

            Node tmp = this.head;
            while(tmp != this.tail) 
            {
                if(name.equalsIgnoreCase(tmp.pageNumber))
                {
                    System.out.println(tmp.value);
                    return true;
                }
                tmp = tmp.getNext();
               
            }
            return false;

        }

        Node pageListcontains(String name) 
        {
            if(this.head == null)
            {
                return null;
            }

            Node tmp = this.head;
            while(tmp != this.tail) 
            {
                if(name.equalsIgnoreCase(tmp.pageNumber))
                {
                    //System.out.println(tmp.Value);
                    return tmp;
                }
                tmp = tmp.getNext();
               
            }
            return null;

        }

        public String givenumber(String loc)
        {
            Node tmp = this.head;
            while(tmp != this.tail) 
            {
                if(tmp.value==loc)
                {return tmp.pageNumber;}

                 tmp=tmp.getNext();
            }
            return null;
        }


        public  void removepage_tail()
        {

                //Node t=this.tail;
                this.tail = this.tail.getPrev();
                this.tail.setNext(null);
                //this.head.setPrev(this.tail);
                this.currSize--;

        }
        public  void removepage_head()
        {
                this.head=this.head.getNext();
                this.head.setPrev(null);
                //this.tail.setNext(this.head);

                this.currSize--;


        }
        public  void removepage_cmp()
        {
            int max=0;
            Scanner sc=new Scanner(System.in);
            int visited[] = new int[13];
            String pdm= new String();
            String[] pt = {"Bhayander","Dahisar","Borivil","Andheri","Andheri East","Parla","ParlaEast","Bandra West","Bandra","Dadar","Worli","Walkeshwar","Marine Drive"};
    
            
            for (int i=0;i<visited.length;i++) 
            {
                if (this.pageListcontains_check_only(pt[i])) 
                {
                visited[i]=1;
                }
            }

            max=0;
            for(int j=0;j<13;j++)
            {
                if(visited[j]==1 && j>=max)
                {
                    max=j;
                }

            }

            String loc = pt[max];

            Node temp=this.head;
            while(temp!=this.tail)
            {
                if(temp.getValue().equals(loc))
                    break;
                else
                {
                temp=temp.getNext();
                }

            }

            String name_temp = temp.getPageNumber();
            String value_temp=temp.getValue();



            temp.setPageNumber(this.tail.getPageNumber());
            temp.setValue(this.tail.getValue());



            this.tail.setPageNumber(name_temp);
            this.tail.setValue(value_temp);
            this.removepage_tail();
}

    public void resize_lru(int size,int newSize)    
    {                                                   
        if(size<newSize)
        {
            this.size=newSize;
        }
        else if(size > newSize)
        {
            while(this.size>newSize)
            {
                this.removepage_tail();
                this.currSize--;
                this.size--;
            }
        

        }
        else
        {
            //do nothing
        }

    }
    
    public void resize_fifo(int size,int newSize)    
    {                                                   
        if(size<newSize)
        {
            this.size=newSize;
        }
        else if(size > newSize)
        {
            while(this.size>newSize)
            {
                this.removepage_head();
                this.currSize--;
                this.size--;
            }
        

        }
        else
        {
            //do nothing
        }

    }
}


class Node 
    {
        //private int pageNumber;
         String pageNumber;
         String value;
        private Node prev;
        private Node next;
        public Node(String pageNumber, String value) 
        {

        this.pageNumber = pageNumber;
        this.value= value;

        }
        public String getPageNumber() 
        {
        return pageNumber;
        }   
         public String getValue() 
        {
        return value;
        }       
        public void setPageNumber(String data) 
        {
            this.pageNumber = data;
        }
        public void setValue(String data) 
        {
            this.value = data;
        }
        public Node getPrev() 
        {
            return prev;
        }
        public void setPrev(Node prev) 
        {
            this.prev = prev;
        }
        public Node getNext() 
        {
            return next;
        }

        public void setNext(Node next) 
        {
            this.next = next;
        }

        public String toString() 
        {
            return pageNumber + "  " + value +"   ";
        }
    }



    class HashDB
{
    //HashMap hm;
    HashMap<String, Node> hm = new HashMap<String,Node>();
    HashDB()
    {
      // Create a hash map
      hm.put("Zara",new Node("Zara","Andheri"));
      hm.put("Mahnaz",new Node("Mahnaz","Bandra"));
      hm.put("Ayan", new Node("Ayan","Worli"));
      hm.put("Daisy",new Node("Daisy","Parla"));
      hm.put("Svetlana",new Node("Svetlana","Andheri East"));
      hm.put("Maurice",new Node("Maurice","Bandra West"));
      hm.put("Ayana",new Node("Ayana","Walkeshwar"));
      hm.put("Damy",new Node("Damy","ParlaEast"));
      hm.put("Amy",new Node("Amy","Andheri"));
      hm.put("Apple",new Node("Apple","Bhayander"));
      hm.put("Caramel",new Node("Caramel","Marine Drive"));
      hm.put("Donna",new Node("Donna","Parla"));
      hm.put("Zeena",new Node("Zeena","Andheri"));
      hm.put("Mince", new Node("Mince","Bandra"));
      hm.put("Fanta",new Node("Fanta","Worli"));
      hm.put("Diana", new Node("Diana","Parla"));
      hm.put("Zeenat",new Node("Zeenat","Dahisar"));
      hm.put("Majorie",new Node("Majorie","Bandra"));
      hm.put("prettyyou", new Node("prettyyou","Dadar"));
      hm.put("Dimsum",new Node("Dimsum","Borivili"));
}
    void display()
    {

        System.out.println("*********Database***********");
        Set set = hm.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) 
        {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
        }


    }

      boolean search(String name)
      {
            if(hm.containsKey(name))
            {

                Object var = hm.get(name);    
                System.out.println(var);
                
                return true;
                
            }

            return false;
      }


      Node loadfromdb(String name)
      {

        if(hm.containsKey(name))
        {
            Node var = hm.get(name);    
            return var;
        }
        return   null;

      }


      public void putindb(String name, String loc)
      {

            hm.put(name,new Node(name,loc));


      }

    
}

class ImagePanel extends JPanel 
{

  private Image img;

  public ImagePanel(String img)
   {
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) 
  {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g) 
  {
    g.drawImage(img, 0, 0, null);
     //super.paintComponent(g);
    //g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
  //}

}
}