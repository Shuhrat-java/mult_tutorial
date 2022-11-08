package uz.mult;

import java.util.ArrayList;
import java.util.Date;

public class JsonClasses {
    public class Default{
        public String url;
        public int width;
        public int height;
    }

    public class High{
        public String url;
        public int width;
        public int height;
    }

    public class Item{
        public String kind;
        public String etag;
        public String id;
        public Snippet snippet;
    }

    public class Maxres{
        public String url;
        public int width;
        public int height;
    }

    public class Medium{
        public String url;
        public int width;
        public int height;
    }

    public class ResourceId{
        public String kind;
        public String videoId;
    }

    public class Root{
        public ArrayList<Item> items;
    }

    public class Snippet{
        public Date publishedAt;
        public String channelId;
        public String title;
        public String description;
        public Thumbnails thumbnails;
        public String channelTitle;
        public String playlistId;
        public int position;
        public ResourceId resourceId;
        public String videoOwnerChannelTitle;
        public String videoOwnerChannelId;
    }

    public class Standard{
        public String url;
        public int width;
        public int height;
    }

    public class Thumbnails{
        public Default mydefault;
        public Medium medium;
        public High high;
        public Standard standard;
        public Maxres maxres;
    }


}
