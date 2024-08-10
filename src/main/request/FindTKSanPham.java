package main.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FindTKSanPham {
    private String keySearch;
    private String keySearchKh;
    private String keySearchDT;
    private java.util.Date startDate;
    private java.util.Date endDate;
}
