package test;

public abstract class Bird extends User{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        long sum = 0L;
        long start = System.currentTimeMillis();
        for(int i=0; i<Integer.MAX_VALUE; i++){
            sum += i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000+"s");
    }
}
