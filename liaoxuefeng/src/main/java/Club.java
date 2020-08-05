import java.io.Serializable;
import java.util.Date;

/**
 * @author WANGJJ
 * @date 2020/05/28
 */
// 俱乐部
public class Club implements Serializable {
    private int id;             // id
    private String name;                // 名称
    private String info;                // 描述
    private Date createDate;    // 创建日期
    private int rank;           // 排名
    // 相应的getter setter不占用篇幅
}