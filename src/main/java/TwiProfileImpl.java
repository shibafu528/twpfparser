/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 shibafu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shibafu on 14/12/17.
 */
class TwiProfileImpl implements TwiProfile {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.JAPAN);
    private Document document;
    private Map<String, StringsCache> cache = new HashMap<>();
    private Map<String, Date> dateCache = new HashMap<>();

    public TwiProfileImpl(Document document) {
        this.document = document;
    }

    /**
     * 指定ID要素の次にある要素を取得します
     * @param id キーエレメントのID
     * @return 要素のテキスト部
     */
    private String getElement(String id) {
        if (!cache.containsKey(id)) {
            try {
                cache.put(id, new StringsCache(document.getElementById(id).nextElementSibling().text()));
            } catch (NullPointerException e) {
                cache.put(id, new StringsCache(""));
            }
        }
        return cache.get(id).get();
    }

    /**
     * 指定された文字列と一致するテキスト要素の次にある要素を取得します
     * @param text キーエレメントのテキスト
     * @param alternate ヒットしなかった場合の代替検索キー
     * @return 要素のテキスト部
     */
    private String getElementByText(String text, String... alternate) {
        if (!cache.containsKey(text)) {
            Elements elements = document.getElementsContainingText(text);
            String searchKey = text;
            for (int i = 0; i < alternate.length && elements.isEmpty(); i++) {
                searchKey = alternate[i];
                elements = document.getElementsContainingText(searchKey);
            }
            for (ListIterator<Element> i = elements.listIterator(elements.size()); i.hasPrevious();) {
                Element element = i.previous();
                if (searchKey.equals(element.text())) {
                    cache.put(text, new StringsCache(element.nextElementSibling().text()));
                    break;
                }
            }
        }
        return cache.get(text).get();
    }

    /**
     * プロフィール上の検索用タグを取得するためのメソッドです<br>
     * 指定ID要素の次にある要素から検索用タグを取得します
     * @param id キーエレメントのID
     * @return 検索用タグの集合
     */
    private Set<String> getElements(String id) {
        if (!cache.containsKey(id)) {
            Set<String> tags = new HashSet<>();
            try {
                for (Element li : document.getElementById(id).nextElementSibling().getElementsByClass("tagCloud").first().children()) {
                    tags.add(li.text());
                }
            } catch (NullPointerException ignored) {}
            cache.put(id, new StringsCache(tags));
        }
        return cache.get(id).getSet();
    }

    /**
     * プロフィール上の日付時刻要素を取得するためのメソッドです<br>
     * 指定された文字列と一致するテキスト要素の次にある要素からテキストを取得し、Dateにパースします
     * @param text キーエレメントのテキスト
     * @param alternate ヒットしなかった場合の代替検索キー
     * @return パースされた日付時刻データ
     */
    private Date parseDate(String text, String... alternate) {
        if (!dateCache.containsKey(text)) {
            Date date;
            try {
                date = dateFormat.parse(getElementByText(text, alternate));
            } catch (ParseException e) {
                date = new Date(0);
            }
            dateCache.put(text, date);
        }
        return dateCache.get(text);
    }

    @Override
    public String getName() {
        return getElement("h-name");
    }

    @Override
    public String getScreenName() {
        return getElement("h-screen-name");
    }

    @Override
    public String getLocation() {
        return getElement("h-location");
    }

    @Override
    public String getWeb() {
        return getElement("h-web");
    }

    @Override
    public String getBiography() {
        return getElement("h-description");
    }

    @Override
    public String getMoreBiography() {
        return getElement("h-motto");
    }

    @Override
    public Set<String> getPersonalTags() {
        return getElements("h-personal");
    }

    @Override
    public Set<String> getLikeTags() {
        return getElements("h-like");
    }

    @Override
    public Set<String> getDislikeTags() {
        return getElements("h-dislike");
    }

    @Override
    public Set<String> getFreeTags() {
        return getElements("h-free");
    }

    @Override
    public int getFollowingsCount() {
        return Integer.valueOf(getElementByText("フォロー"));
    }

    @Override
    public int getFollowersCount() {
        return Integer.valueOf(getElementByText("フォロワー"));
    }

    @Override
    public int getTweetsCount() {
        return Integer.valueOf(getElementByText("ツイート"));
    }

    @Override
    public int getFavoritesCount() {
        return Integer.valueOf(getElementByText("お気に入り"));
    }

    @Override
    public int getListedCount() {
        return Integer.valueOf(getElementByText("リスト"));
    }

    @Override
    public float getTweetPerDay() {
        Matcher matcher = Pattern.compile("(\\d\\.\\d)( / 日)?").matcher(getElementByText("平均ツイート", "ツイート/日"));
        if (matcher.find()) {
            return Float.valueOf(matcher.group(1));
        } else {
            return 0.0f;
        }
    }

    @Override
    public int getElapsed() {
        return Integer.valueOf(getElementByText("Twitter歴").replace("日", ""));
    }

    @Override
    public Date getSince() {
        return parseDate("Twitter登録日", "Twitter登録");
    }

    @Override
    public Date getProfileSince() {
        return parseDate("プロフ作成日", "プロフ作成");
    }

    @Override
    public Date getProfileUpdated() {
        return parseDate("プロフ更新日", "プロフ更新");
    }

    @Override
    public Date getApiUsed() {
        return parseDate("API取得完了");
    }

    @Override
    public Date getNextApiUse() {
        return parseDate("API取得予定");
    }

    private static class StringsCache {
        private String s;
        private Set<String> set;

        public StringsCache(String s) {
            this.s = s;
        }

        public StringsCache(Set<String> set) {
            this.set = set;
        }

        public String get() {
            return s;
        }

        public Set<String> getSet() {
            return set;
        }
    }
}
