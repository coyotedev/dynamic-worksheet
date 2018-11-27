package dynamicworksheet.type;

import com.google.gson.annotations.SerializedName;

/**
 * Ориентация контейнера
 */
public enum ContainerLayoutType {
    @SerializedName("horizontal")
    Horizontal,
    @SerializedName("vertical")
    Vertical
}
