package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model;

import java.util.List;

/**
 * Created by MNArifin on 16/05/2017.
 */

public class ResultResponse
{
    //    "status": "ok",
    //    "results": [{
    //            "id": "abc-news-au",
    //            "name": "ABC News (AU)",
    //            "description": "Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.",
    //            "url": "http://www.abc.net.au/news",
    //            "category": "general",
    //            "language": "en",
    //            "country": "au",
    //            "urlsToLogos": {
    //                "small": "",
    //                "medium": "",
    //                "large": ""
    //            },
    //            "sortBysAvailable": ["top"]
    //            },]

    public int page;
    public List<Result> results;
}