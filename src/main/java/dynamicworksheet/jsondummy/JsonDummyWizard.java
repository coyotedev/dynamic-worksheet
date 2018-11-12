package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

public class JsonDummyWizard extends JsonDummyContainer {
    @SerializedName("next")
    public final String mNextCaption = "";
    @SerializedName("prev")
    public final String mPrevCaption = "";
}
