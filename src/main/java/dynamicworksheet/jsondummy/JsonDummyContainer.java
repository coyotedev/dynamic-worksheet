package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.types.ContainerLayoutType;

import java.util.ArrayList;
import java.util.List;

public class JsonDummyContainer extends JsonDummyBase {
    @SerializedName("layout")
    public ContainerLayoutType mLayoutType = ContainerLayoutType.Horizontal;
    @SerializedName("c")
    public final List<IJsonDummy> mChilds = new ArrayList<>();
}
