package com.droid.lokalplayground;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AdListableConstants {
    //Remote config ids for ads_position_config
    public static final String ARTICLE_RECOMMENDATION = "-1";
    public static final String VIDEO_RECOMMENDATION = "-2";
    public static final String AD_MOB_ADS_IN_ARTICLE_RECOMMENDATION = "-3";
    public static final String AD_MOB_ADS_IN_VIDEO_RECOMMENDATION = "-4";
    public static final String CONSTITUENCY_FEED = "constituency_feed";
    public static final String ARTICLE_RECOMMENDATION_FEED = "article_recommendation_feed";//Used for ad-manager
    public static final String VIDEO_RECOMMENDATION_FEED = "video_recommendation_feed";//Used for ad-manager

    @StringDef({ARTICLE_RECOMMENDATION, VIDEO_RECOMMENDATION, AD_MOB_ADS_IN_ARTICLE_RECOMMENDATION, AD_MOB_ADS_IN_VIDEO_RECOMMENDATION, CONSTITUENCY_FEED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FeedType {}

    public static class PlacementType {
        public static final int ARTICLE_PAGE = 1;
        public static final int CATEGORY = 2;
        public static final int RECOMMENDATIONS = 3;//Same as Article page, don't use this
        public static final int FEATURED = 4;
        public static final int TICKER = 5;
        public static final int SERVICE = 6;
        public static final int MATRIMONY_PROFILE_NOT_CREATED = 7;
        public static final int MATRIMONY_MALE_DOCUMENT_NOT_SUBMITTED = 8;
        public static final int MATRIMONY_MALE_VERIFICATION_FAILED = 9;
        public static final int MATRIMONY_MALE_VERIFIED_NOT_PAID = 10;
        public static final int MICRO_LOCATION_BASED_LISTINGS = 11;
        public static final int STRIP_BANNER = 12;
        public static final int MATRIMONY_FEMALE_DOCUMENT_NOT_SUBMITTED = 13;
        public static final int MATRIMONY_FEMALE_VERIFICATION_FAILED = 14;
        public static final int MATRIMONY_FEMALE_VERIFIED_NOT_PAID = 15;
    }

    public static class AdServerCardType {
        public static final int TYPE_AD_MANAGER_NATIVE_AD = 995;
        public static final int TYPE_AD_MOB_FULL_SCREEN_NATIVE_AD = 996;
        public static final int TYPE_BUY_AND_SELL_EMPTY_LIST = 997;
        public static final int TYPE_CLASSIFIED_FEATURED = 998;
        public static final int TYPE_ADMOB_NATIVE_AD = 999;
        public static final int TYPE_AD_CARD_IMAGE = 1001;
        public static final int SPONSORED_ARTICLE = 1002;
        public static final int TYPE_AD_CARD_VIDEO = 1003;
        public static final int INFO_HTML_CARD = 1004;
        public static final int SHAREABLE_CARD = 1005;
        public static final int STORIES = 1006;
        public static final int TYPE_AD_BANNER = 1007;
        public static final int TYPE_JOBS_CONTENT = 1008;
        public static final int TYPE_JOBS_IMAGE = 1009;
        public static final int TYPE_JOBS_VIDEO = 1010;
        public static final int TYPE_MATRIMONY_PROFILE = 1011;
        public static final int TYPE_CLASSIFIED_MULTIPLE = 1012;
        public static final int TYPE_CLASSIFIED_NUDGE = 1013;
        public static final int TYPE_CLASSIFIED_TOP_VERTICAL_NUDGE = 1015; // orange color nudges at top
        public static final int TYPE_CLASSIFIED_SUBMISSION_NUDGE = 1014;
        public static final int CLASSIFIED_SUBMISION_NUDGESUBLIST_ITEM_TYPE = 1016;
        public static final int CLASSIFIED_CATEGORY_NUDGESUBLIST_ITEM_TYPE = 1017;
        public static final int TYPE_MATRIMONY_FOOTER = 1018;
        public static final int TYPE_BUY_AND_SELL_ARTICLE = 1019;
        public static final int TYPE_BUY_AND_SELL_VIDEO= 1020;
        public static final int TYPE_COMMUNITY_NUDGES = 1021;
        public static final int TYPE_BUY_AND_SELL_FILTERS = 1022;
        public static final int TYPE_AD_CARD_WEBVIEW = 1023;
        public static final int TYPE_BUY_AND_SELL_RECOMMENDATION = 1024;
        public static final int TYPE_BUY_AND_SELL_SELECTED_FILTERS = 1025;
        public static final int TYPE_JOBS_FILTERS = 1026;
        public static final int TYPE_CLASSIFIED_RECOMMENDATION_HEADER = 1027;
        public static final int TYPE_JOBS_PROMOTED_POSTS_SECTION = 1028;
        public static final int TYPE_STRIP_BANNER_AD = 1029;
        public static final int TYPE_AD_CARD_HORIZONTAL_SWIPE = 1030;
        public static final int TYPE_FULL_SCREE_IMAGE_AD = 1031;
        public static final int TYPE_REAL_ESTATE_HOME_FEED_CARD = 1033;
        public static final int TYPE_MATRIMONY_HOME_FEED_CARD = 1032;
        public static final int TYPE_MATRIMONY_PROFILE_ITEM = 1034;
        public static final int TYPE_MATRIMONY_VIEW_MORE_ITEM = 1035;
    }

    public static class AdLinkType {
        public static final int NO_ACTION = 0;
        public static final int EXTERNAL_URL = 1;
        public static final int INTERNAL_URL = 2;
        public static final int PHONE_NUMBER = 3;
        public static final int CATEGORY = 4;//
        public static final int OPEN_WEB_FORM = 5;
    }

    public static class AdActionType {
        public static final String CALL = "call";
        public static final String ACTION = "action";
        public static final String INTERNAL_URI = "internal_uri";
        public static final String EXTERNAL_URL = "external_url";
    }

    public static class AttachmentType {
        public static final int TEXT = 0;
        public static final int IMAGE = 1;
        public static final int VIDEO = 2;
        public static final int UNKNOWN = 3;
        public static final int WEBVIEW = 4;
    }

    public static class ObjectType {
        public static final int POST_OBJECT = 1;
        public static final int AD_OBJECT = 2;
        public static final int AD_MOB_OBJECT = 3;
        public static final int FILTER_OBJECT = 4;
    }

    public static final int OTHER_FEEDBACK_OPTION = 5;

    public static final int DOWNLOADED = 2000;
    public static final int DOWNLOADING = 2001;
    public static final int ERROR = 2002;

    @IntDef({DOWNLOADED, DOWNLOADING, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoadStatus {
    }

    public static final int UPLOADED = 4000;
    public static final int UPLOADING = 4001;
    public static final int UPLOAD_ERROR = 4002;

    @IntDef({UPLOADED, UPLOADING, UPLOAD_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UploadStatus {
    }


    public static class Gender {
        public static final int NEUTRAL = 0;
        public static final int FEMALE = 1;
        public static final int MALE = 2;
    }

    public static class MatrimonyTabs {
        public static final int MY_MATCHES_CONTAINER = 0;
        public static final int ALL_PROFILES = 1;
        public static final int MY_MATCHES = 2;
        public static final int REQUEST_SENT = 3;
        public static final int REQUEST_RECEIVED = 4;
    }

    public static class RefreshTabsStatusCode {
        public static final int MY_MATCHES = 2;
        public static final int REQUEST_SENT = 3;
        public static final int REQUEST_RECEIVED = 4;
        public static final int REVEAL_CONTACT = 5;// To refresh both REQUEST_SENT and MY_MATCHES tabs
    }

    public static class MatrimonyTabsEngName {
        public static final String MY_MATCHES_CONTAINER = "my_matches_container";
        public static final String ALL_PROFILES = "all_profiles";
        public static final String MY_MATCHES = "my_matches";
        public static final String REQUEST_SENT = "request_sent";
        public static final String REQUEST_RECEIVED = "request_received";
    }

    public static class MatrimonyProfileStatus {
        public static final int PROFILE_NOT_EXIST = 0;
        public static final int DEFAULT = 1;//Profile is created, but no verification docs are uploaded yet
        public static final int VERIFICATION_SUBMITTED = 2;//Verification document submitted
        public static final int VERIFICATION_INPROCESS = 3;//Verification in process
        public static final int VERIFICATION_FAILED = 4;
        public static final int VERIFIED = 5;
        public static final int UNPUBLISHED = 6;
        public static final int BLOCKED = 7;
    }

    public static class MatrimonyRequestStatus {
        public static final int REQUEST_LIMIT_REACHED = 1;//DAILY_LIMIT_REACHED
        public static final int REQUEST_SENT = 2;
        public static final int REQUEST_MATCHED = 3;
        public static final int ALREADY_REQUESTED = 4;
        public static final int REVEALED_CONTACT = 5;//For reveal contact info
    }

    public static class MatrimonyProfileFormPart {
       public static final String PERSONAL_INFO = "personal_info";
       public static final String SOCIAL_INFO = "social_info";
       public static final String PACKAGE_INFO = "package_info";
       public static final String EDUCATION_INFO = "education_info";
       public static final String PROFESSIONAL_INFO = "professional_info";
       public static final String EXTRA_INFO = "extra_info";
       public static final String CONTACT_INFO = "contact_info";
    }


    public static class MatrimonyFormFields {
        public static final String PROFILE_FOR = "profile_for";
        public static final String NAME = "name";
        public static final String HEIGHT = "height";
        public static final String MOTHER_TONGUE = "mother_tongue";
        public static final String RELIGION = "religion";
        public static final String CASTE = "caste";
        public static final String STATE = "state";
        public static final String HOME_TOWN = "home_town";
        public static final String CURRENTLY_LIVING = "currently_living";
        public static final String EDUCATION = "education";
        public static final String EMPLOYED_IN = "employed_in";
        public static final String MONTHLY_INCOME = "monthly_income";
        public static final String MARTIAL_STATUS = "marital_status";
        public static final String GENDER = "gender";
        public static final String DIFFERENTLY_ABLED = "differently_abled";
        public static final String DIETARY_HABITS = "dietary_habits";
        public static final String DOB = "dob";
    }

    public static class MatrimonyVariant {
        public static final int DO_NOT_SHOW_MATRIMONY = 0;
        public static final int SHOW_MATRIMONY_WITH_ENTRY_POINT = 1;
        public static final int SHOW_MATRIMONY_WITHOUT_ENTRY_POINT = 2;
    }

    public static class RangeStrings {
        public static final String MIN = "min";
        public static final String MAX = "max";
    }


    public static final int SELECTED_FILTERS_COUNT_PAYLOAD = 1;
    public static final int FILTER_SELECTED_PAYLOAD = 2;
    public static final int CHECK_BOX_FILTER_PAYLOAD =  3;
    public static final int RANGE_FILTER_PAYLOAD =  4;
    public static final int SINGLE_SELECT_FILTER_PAYLOAD =  5;
    @IntDef({SELECTED_FILTERS_COUNT_PAYLOAD, FILTER_SELECTED_PAYLOAD, CHECK_BOX_FILTER_PAYLOAD, RANGE_FILTER_PAYLOAD, SINGLE_SELECT_FILTER_PAYLOAD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FiltersPayloadType {}

    public static final String MATRIMONY_FILTER_CATEGORY_ID_TEST = "54";
    public static final String MATRIMONY_FILTER_CATEGORY_ID_PROD = "84";

    public static class FeedbackTypes {
        public static final int FEEDBACK_TYPE_MATRIMONY_REPORT = 1;
        public static final int FEEDBACK_TYPE_MATRIMONY_DELETE_PROFILE = 2;
        public static final int FEEDBACK_TYPE_MATRIMONY_PAYMENT_RELATED = 3;

    }

    public static class SubmissionTypes {
        public static final int DEFAULT = 0;
        public static final int JOBS = 2;
        public static final int MATRIMONIAL = 9;
        public static final int REAL_ESTATE = 10;
    }

    public static class UserRole {
        public static final int EMPLOYER = 1;
        public static final int CANDIDATE = 2;
        public static final int ADVERTISER = 3;
        public static final int MATRIMONY = 4;
    }

    public static class PaymetStatus {
        public static final int NOT_INITIATED = 0;
        public static final int PENDING = 1;
        public static final int PAID = 2;
        public static final int DONE_ON_RAZORPAY = 3;// Payment is successful in Razorpay, but the package is not assigned to user in our server
    }

    public static class RazorPayNotesFields {
        public static final String TYPE = "type";
        public static final String PACKAGE_ID = "packge_id";

    }

    public static class FragmentTypes {
        public static final int POST_TYPE = 1;
        public static final int CLASSIFIED_TYPE = 2;
    }

    public static class ClassifiedCategory {
        public static final int JOB = 2;
        public static final int BUY_AND_SELL = 13;
    }

    public static class ActivityOpenedFrom {
        public static final int DEFAULT = 0;
        public static final int NOTIFICATION = 1;
        public static final int NOTIFICATION_HUB =  2;
        public static final int DEEP_LINK =  3;
    }

    public static class BuyAndSellField {
        public static final String DETAILS = "details";
        public static final String PRICE = "pricing";

    }

    public static class ClassifiedServiceId {
        public static final String JOBS = "1" ;
        public static final String MATRIMONY = "2" ;
        public static final String BUY_AND_SELL = "3" ;
        public static final String BUSINESS_LISTINGS = "34" ;

    }

    public static class ClassifiedSubmissionId {
        public static final String JOBS = "2" ;
        public static final String BUY_AND_SELL = "4" ;
        public static final String MATRIMONY = "9" ;
        public static final String REAL_ESTATE = "10" ;

    }

    public static class FaqTypes {
        public static final String MATRIMONY_PAYMENT = "1" ;
    }

    public static class VerticalNudgeBackgroundType {
        public static final int DEFAULT = 1;
        public static final int COMMUNITY_NUDGE = 2;
    }

    public static class BuyAndSellChildrenStatus {
        public static final int PRESENT = 1;
        public static final int ABSENT = 2;
    }

    public static class APIMethodCallType {
        public static final int GET = 0;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int PATCH = 3;
        public static final int DELETE = 4;
    }

    public static class MatrimonyLaunchVariant {
        public static final String DEFAULT_FLOW = "1";
        public static final String OPEN_CREATION_FLOW = "2";
    }

    public static class MatrimonyVerificationVariant {
        public static final String DEFAULT_FLOW = "1";
        public static final String OPEN_VERIFICATION_FLOW = "2";
    }

    public static class MatrimonyLandingPageVariant {
        public static final String DEFAULT_FLOW = "1"; //No landing page
        public static final String OPEN_LANDING_PAGE = "2";
        public static final String OPEN_LANDING_PAGE_WITH_STICKY_BUTTON = "3";
    }

    public static class MatrimonyRestrictedAccessVariant {
        public static final String DEFAULT_FLOW = "1"; //No Restriction
        public static final String RESTRICT_ACCESS = "2";
    }

    public static final int NO_CALL = 20000;
    public static final int CREATE = 20001;
    public static final int REPLACE = 20002;
    public static final int DELETE = 20003;

    @IntDef({NO_CALL, CREATE, REPLACE, DELETE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ApiCallType {
    }

    public static final int MATRIMONY_PROFILE_PICS_MAX_COUNT = 6;
}
