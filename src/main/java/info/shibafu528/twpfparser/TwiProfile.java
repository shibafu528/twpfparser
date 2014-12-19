package info.shibafu528.twpfparser;
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

import java.util.Date;
import java.util.Set;

/**
 * Created by shibafu on 14/12/17.
 */
public interface TwiProfile {
    public String getName();

    public String getScreenName();

    public String getProfileImageUrl();

    public String getLocation();

    public String getWeb();

    public String getBiography();

    public String getBiographyHtml();

    public String getMoreBiography();

    public String getMoreBiographyHtml();

    public Set<String> getPersonalTags();

    public Set<String> getLikeTags();

    public Set<String> getDislikeTags();

    public Set<String> getFreeTags();

    public int getFollowingsCount();

    public int getFollowersCount();

    public int getTweetsCount();

    public int getFavoritesCount();

    public int getListedCount();

    public float getTweetPerDay();

    public int getElapsed();

    public Date getSince();

    public Date getProfileSince();

    public Date getProfileUpdated();

    public Date getApiUsed();

    public Date getNextApiUse();
}
