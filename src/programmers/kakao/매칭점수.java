package programmers.kakao;

import java.util.*;
import java.util.regex.*;

public class 매칭점수 {

  private static Map<String, WebPage> webPageMap;

  public int solution(String word, String[] pages) {
    int answer = 0;

    webPageMap = new HashMap<>();
    List<WebPage> webPageList = new ArrayList<>();

    for(int i = 0; i < pages.length; i++) {

      String html = pages[i];
      html.replaceAll("\\n", " ");

      WebPage webpage = new WebPage(i, html.toLowerCase());
      webpage.calculateBaseScore(word.toLowerCase());
      webPageList.add(webpage);
    }

    for(WebPage webpage : webPageList) {
      webpage.calculateLinkScore();
    }
    for(WebPage webpage : webPageList) {
      webpage.calculateMatchingScore();
    }

    Collections.sort(webPageList);

    answer = webPageList.get(0).index;

    return answer;
  }


  private class WebPage implements Comparable<WebPage> {

    private Pattern URL_PATTERN = Pattern.compile("<meta property=\"og:url\" content=\"https://(.*?)\"/>");
    private Pattern EXTERNAL_LINK_PATTERN = Pattern.compile("<a href=\"https://(.*?)\">(.*?)</a>");

    int index;
    String url;
    String html;

    List<String> externalLinks;

    int baseScore;
    double linkScore;
    double matchingScore;

    WebPage(int index, String html) {
      this.index = index;
      this.html = html;
      this.externalLinks = new ArrayList<>();
      findUrl();
      findExternalLink();
    }

    @Override
    public int compareTo(WebPage o) {
      if(this.matchingScore == o.matchingScore) return this.index - o.index;
      return Double.compare(o.matchingScore, this.matchingScore);
    }

    public void calculateLinkScore() {
      for(String externalLink : externalLinks) {
        if(webPageMap.containsKey(externalLink)) {
          WebPage w = webPageMap.get(externalLink);
          w.linkScore += ((double) baseScore / externalLinks.size());
        }
      }
    }

    public void calculateBaseScore(String word) {

      int pos = html.indexOf(word);

      while(pos != -1) {

        char pre = html.charAt(pos - 1);
        char post = html.charAt(pos + word.length());

        if(!Character.isLowerCase(pre) && !Character.isLowerCase(post)) baseScore++;
        pos = html.indexOf(word, pos + 1);
      }
    }

    public void calculateMatchingScore() {
      matchingScore = (double) baseScore + linkScore;
    }

    private void findUrl() {
      Matcher matcher = URL_PATTERN.matcher(html);
      while(matcher.find()) {
        url = matcher.group(1).replaceAll(" ", "");
      }
      webPageMap.put(url, this);
    }

    private void findExternalLink() {
      Matcher matcher = EXTERNAL_LINK_PATTERN.matcher(html);

      while(matcher.find()) {
        externalLinks.add(matcher.group(1).replace(" ", ""));
      }
    }

  }
}
