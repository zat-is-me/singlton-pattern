/**
 * @author Tatek Ahmed on 12/7/2024
 */
class Singleton {

    // 1. Eager Initialization (Instance is created at the time of class loading)
    public static final Singleton INSTANCE = new Singleton();

    private Singleton(){
        // Prevent reflection
        if (INSTANCE != null)
            throw new IllegalStateException("Instance already created!");
    }

    // 3. Public Method to Provide Access to the Instance
    public static Singleton getInstance(){
        return INSTANCE;
    }

    // 4. Prevent Singleton from Serialization and Deserialization
    public Object readResolve(){
        return getInstance();
    }

    // 5. Ensure Singleton in Multi-Threaded Environments
    public static volatile Singleton volatileInstance;

    public static Singleton getVolatileInstance() {
        if (volatileInstance == null){
            synchronized (Singleton.class){
                if (volatileInstance == null)
                    volatileInstance = new Singleton();
            }
        }
        return volatileInstance;
    }

    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        System.out.println(singleton1.hashCode());
        System.out.println(singleton2.hashCode());

        NonSinglton nonSinglton = new NonSinglton();
        NonSinglton nonSinglton1 = new NonSinglton();

        System.out.println(nonSinglton1.hashCode());
        System.out.println(nonSinglton.hashCode());
    }
}
