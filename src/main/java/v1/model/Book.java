package v1.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean available;
    private String reservedBy;
}
