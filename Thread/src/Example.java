import static java.lang.System.*;

 

class SharedObj

{

  public synchronized void useSynch(String name)

  {

    err.println(name + " use before");

    try{Thread.sleep(1000);} catch (InterruptedException e){e.printStackTrace();}

    err.println(name + " use after");

  }

  public void useHalfSynch(String name)

  {

    synchronized(this){

      err.println(name + " use before");

    }

    try{Thread.sleep(1000);} catch (InterruptedException e){e.printStackTrace();}

    err.println(name + " use after");

  }

}

 

 

class SmallThread extends Thread//Thread를 상속받았다.

{

  SharedObj obj;//동일한 객체를 가리키도록 설정될 변수

  public SmallThread(SharedObj obj)//생성자

  {

    this.obj = obj;

  }

  public void run()

  {

    //obj.useSynch(this.getName());

    obj.useHalfSynch(this.getName());

  }

 

}


  public static void raceCondition()

  {

    //객체 하나만 만들고 5개의 쓰레드가 모두 동일한 객체를 공유한다.

    SharedObj obj = new SharedObj();

    SmallThread st1 = new SmallThread(obj);

    SmallThread st2 = new SmallThread(obj);

    SmallThread st3 = new SmallThread(obj);

    SmallThread st4 = new SmallThread(obj);

    SmallThread st5 = new SmallThread(obj);

 

    st1.start();

    st2.start();

    st3.start();

    st4.start();

    st5.start();

  }