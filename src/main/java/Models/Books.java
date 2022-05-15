package Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class Books {


    private ArrayList<CollectionOfIsbn> collectionOfIsbns;
    private String userId;

}
