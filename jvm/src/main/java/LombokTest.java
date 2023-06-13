import lombok.Builder;

/**
 * LombokTest
 *
 * @author pglc1026
 * @date 2019-08-09
 */
@Builder(toBuilder = true)
public class LombokTest {

    private String name;

    private int age;

    public static void main(String[] args) {
        LombokTest build = LombokTest.builder().name("111").age(1111).build();
        LombokTest build1 = build.toBuilder().age(222).name("2222").build();
        System.out.println(build == build1);
    }

}
