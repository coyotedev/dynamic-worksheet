package dynamicworksheet.validation;

import dynamicworksheet.util.mutablevalue.MutableFileParams;
import dynamicworksheet.util.mutablevalue.MutableValue;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUpload extends ValidationBase {

    private static final String REGEX_FILE_SIZE = "(^[0-9]+)(b|[Kk]b|[Mm]b|[Gg]b){1}";
    private static final String REGEX_FILE_EXTENSIONS_SPLITTER = ",";
    private static final String REGEX_FILE_EXTENSIONS = "((^|,[ ]*)(png|jpg|jpeg|bmp))+$";
    private static final int REGEX_FILE_SIZE_GROUP_NUMBER = 1;
    private static final int REGEX_FILE_SIZE_GROUP_DIMENS = 2;
    private static final HashMap<String, Integer> SIZE_DIMENS_ORDER = new HashMap<String, Integer>(){{
        put("b", 0);
        put("kb", 1);
        put("Kb", 1);
        put("mb", 2);
        put("Mb", 2);
        put("gb", 3);
        put("Gb", 3);
    }};

    private final MutableFileParams mParams;
    private final MutableFileParams mReference;

    public ValidationUpload(MutableFileParams params, MutableFileParams ref, String error) {
        super(error);
        mParams = params;
        mReference = ref;
    }

    @Override
    public boolean isPassed() {
        return mReference.getValue().isFits(mParams.getValue());
    }

    public static class FileParams {
        private String mSize;
        private String mExtensions;
        private Integer mMinWidth, mMaxWidth;
        private Integer mMinHeight, mMaxHeight;

        private FileParams(Builder builder) throws IllegalArgumentException {
            mSize = builder.mMaxSize;
            mExtensions = builder.mExtensions;
            mMinWidth = builder.mMinWidth;
            mMaxWidth = builder.mMaxWidth;
            mMinHeight = builder.mMinHeight;
            mMaxHeight = builder.mMaxHeight;
            checkValid();
        }

        public boolean isFits(FileParams other) {
            {
                Pattern pattern = Pattern.compile(REGEX_FILE_SIZE);
                Matcher matcherThis = pattern.matcher(mSize);
                Matcher matcherOther = pattern.matcher(other.mSize);
                if (matcherThis.find() && matcherOther.find()) {
                    int dimensThis = SIZE_DIMENS_ORDER.get(matcherThis.group(REGEX_FILE_SIZE_GROUP_DIMENS));
                    int dimensOther = SIZE_DIMENS_ORDER.get(matcherOther.group(REGEX_FILE_SIZE_GROUP_DIMENS));
                    int bytesThis = Integer.parseInt(matcherThis.group(REGEX_FILE_SIZE_GROUP_NUMBER));
                    int bytesOther = Integer.parseInt(matcherOther.group(REGEX_FILE_SIZE_GROUP_NUMBER));

                    if (dimensThis < dimensOther) { // FIXME: переделать это говно
                        return false;
                    } else if (dimensThis == dimensOther) {
                        if (bytesThis < bytesOther) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            for (String it : other.mExtensions.split(REGEX_FILE_EXTENSIONS_SPLITTER)) {
                if (!mExtensions.contains(it)) {
                    return false;
                }
            }

            return true;
        }

        public void checkValid() throws IllegalArgumentException {
            {
                Pattern pattern = Pattern.compile(REGEX_FILE_SIZE);
                Matcher matcher = pattern.matcher(mSize);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("File size argument is broken!");
                }
            }
            {
                Pattern pattern = Pattern.compile(REGEX_FILE_EXTENSIONS);
                Matcher matcher = pattern.matcher(mExtensions);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("File extensions argument is broken!");
                }
            }
            if (mMinWidth < 0 || mMaxWidth < 0 || mMinHeight < 0 || mMaxHeight < 0) {
                throw new IllegalArgumentException("File size arguments is broken: cannot be less then 0!");
            }
            if (mMinWidth > mMaxWidth || mMinHeight > mMaxHeight) {
                throw new IllegalArgumentException("File size arguments is broken: max args cannot be less then min!");
            }
        }

        public static class Builder {
            private static final String FILE_SIZE = "1Mb";
            private static final String FILE_EXTENSIONS = "png, jpg";
            private static final Integer MIN_WIDTH = 0;
            private static final Integer MAX_WIDTH = 1920;
            private static final Integer MIN_HEIGHT = 0;
            private static final Integer MAX_HEIGHT = 1080;

            private String mMaxSize = FILE_SIZE;
            private String mExtensions = FILE_EXTENSIONS;
            private Integer mMinWidth = MIN_WIDTH, mMaxWidth = MAX_WIDTH;
            private Integer mMinHeight = MIN_HEIGHT, mMaxHeight = MAX_HEIGHT;

            public Builder setFileSize(String pattern) {
                mMaxSize = pattern;
                return this;
            }

            public Builder setExtensions(String pattern) {
                mExtensions = pattern;
                return this;
            }

            public Builder setMinWidth(int value) {
                mMinWidth = value;
                return this;
            }

            public Builder setMaxWidth(int value) {
                mMaxWidth = value;
                return this;
            }

            public Builder setMinHeight(int value) {
                mMinHeight = value;
                return this;
            }

            public Builder setMaxHeight(int value) {
                mMaxHeight = value;
                return this;
            }

            public FileParams build() throws IllegalArgumentException {
                return new FileParams(this);
            }
        }
    }
}
