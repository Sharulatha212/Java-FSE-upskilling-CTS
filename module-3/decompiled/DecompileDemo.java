/*
 * Decompiled with CFR 0.152.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DecompileDemo {
    private final String name;
    protected int value;
    public static final double PI = 3.14159;

    public DecompileDemo(String string, int n) {
        this.name = string;
        this.value = n;
    }

    public List<Integer> processNumbers(List<Integer> list) {
        return list.stream().filter(n -> n > 0).map(n -> n * this.value).collect(Collectors.toList());
    }

    public <T> void processWithResource(final T t) {
        try (AutoCloseable autoCloseable = new AutoCloseable(){

            @Override
            public void close() throws Exception {
                System.out.println("Closing resource for: " + String.valueOf(t));
            }
        };){
            System.out.println("Processing: " + String.valueOf(t));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public synchronized Status checkStatus() {
        if (this.value > 0) {
            return Status.ACTIVE;
        }
        if (this.value < 0) {
            return Status.INACTIVE;
        }
        return Status.PENDING;
    }

    public static void main(String[] stringArray) {
        DecompileDemo decompileDemo = new DecompileDemo("Test", 5);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println("Processed numbers: " + String.valueOf(decompileDemo.processNumbers(arrayList)));
        decompileDemo.processWithResource("Sample Data");
        DecompileDemo decompileDemo2 = decompileDemo;
        Objects.requireNonNull(decompileDemo2);
        InnerHelper innerHelper = new InnerHelper(decompileDemo2, "DEBUG");
        System.out.println(innerHelper.formatMessage("Test message"));
        System.out.println("Status: " + String.valueOf((Object)decompileDemo.checkStatus()));
    }

    private static enum Status {
        ACTIVE,
        INACTIVE,
        PENDING;

    }

    private class InnerHelper {
        private String prefix;

        InnerHelper(DecompileDemo decompileDemo, String string) {
            this.prefix = string;
        }

        String formatMessage(String string) {
            return this.prefix + ": " + string;
        }
    }
}
