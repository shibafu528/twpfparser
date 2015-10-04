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

package info.shibafu528.twpfparser;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;

public class TwiProfileImplTest {

    private static TwiProfile profile;

    @BeforeClass
    public static void setUp() throws Exception {
        profile = TwiProfileFactory.getTwiProfile("grslf", UserAgent.UA_ANDROID_JB);
    }

    @Test
    public void testGetName() throws Exception {
        String name = profile.getName();
        System.out.println("Name:" + name);
        assertEquals(name, "grassleaf");
    }

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = profile.getScreenName();
        System.out.println("ScreenName:" + screenName);
        assertEquals(screenName, "@grslf");
    }

    @Test
    public void testGetProfileImageUrl() throws Exception {
        String profileImageUrl = profile.getProfileImageUrl();
        System.out.println("ProfileImageUrl:" + profileImageUrl);
        assertTrue(profileImageUrl.contains("pbs.twimg.com"));
    }

    @Test
    public void testGetLocation() throws Exception {
        String location = profile.getLocation();
        System.out.println("Location:" + location);
        assertEquals(location, "草原");
    }

    @Test
    public void testGetWeb() throws Exception {
        String web = profile.getWeb();
        System.out.println("Web:" + web);
        assertEquals(web, "http://twpf.jp/grslf");
    }

    @Test
    public void testGetBiography() throws Exception {
        String biography = profile.getBiography();
        System.out.println("Bio:" + biography);
        assertTrue(biography.contains("Owner: @shibafu528"));
    }

    @Test
    public void testGetMoreBiography() throws Exception {
        String moreBiography = profile.getMoreBiography();
        System.out.println("More:" + moreBiography);
        assertTrue(moreBiography.contains("@shibafu528のサブアカウントですっ"));
    }

    @Test
    public void testGetPersonalTags() throws Exception {
        Set<String> personalTags = profile.getPersonalTags();
        for (String personalTag : personalTags) {
            System.out.println("PersonalTag:" + personalTag);
        }
        assertTrue(personalTags.contains("サブアカウント"));
    }

    @Test
    public void testGetLikeTags() throws Exception {
        Set<String> likeTags = profile.getLikeTags();
        for (String likeTag : likeTags) {
            System.out.println("LikeTag:" + likeTag);
        }
        assertTrue(likeTags.contains("結月ゆかり"));
    }

    @Test
    public void testGetDislikeTags() throws Exception {
        Set<String> dislikeTags = profile.getDislikeTags();
        for (String dislikeTag : dislikeTags) {
            System.out.println("DislikeTag:" + dislikeTag);
        }
        assertTrue(dislikeTags.contains("R-18G"));
    }

    @Test
    public void testGetFreeTags() throws Exception {
        Set<String> freeTags = profile.getFreeTags();
        for (String freeTag : freeTags) {
            System.out.println("FreeTag:" + freeTag);
        }
        assertTrue(freeTags.contains("(^ω^ ≡ ^ω^)ゆかりさんゆかりさん"));
    }

    @Test
    public void testGetFollowingsCount() throws Exception {
        int followingsCount = profile.getFollowingsCount();
        System.out.println("Follow:" + followingsCount);
        assertTrue(followingsCount >= 0);
    }

    @Test
    public void testGetFollowersCount() throws Exception {
        int followersCount = profile.getFollowersCount();
        System.out.println("Follower:" + followersCount);
        assertTrue(followersCount > 0);
    }

    @Test
    public void testGetTweetsCount() throws Exception {
        int tweetsCount = profile.getTweetsCount();
        System.out.println("Tweets:" + tweetsCount);
        assertTrue(tweetsCount > 0);
    }

    @Test
    public void testGetFavoritesCount() throws Exception {
        int favoritesCount = profile.getFavoritesCount();
        System.out.println("Favorites:" + favoritesCount);
        assertTrue(favoritesCount > 0);
    }

    @Test
    public void testGetListedCount() throws Exception {
        int listedCount = profile.getListedCount();
        System.out.println("Listed:" + listedCount);
        assertTrue(listedCount >= 0);
    }

    @Test
    public void testGetTweetPerDay() throws Exception {
        float tweetPerDay = profile.getTweetPerDay();
        System.out.println("Tweet per day:" + tweetPerDay + " tw/d");
        assertTrue(tweetPerDay > 0.0f);
    }

    @Test
    public void testGetElapsed() throws Exception {
        int elapsed = profile.getElapsed();
        System.out.println("Elapsed:" + elapsed + " days");
        assertTrue(elapsed > 0);
    }

    @Test
    public void testGetSince() throws Exception {
        Date since = profile.getSince();
        System.out.println("Since:" + since);
        assertEquals(since, new Date(1388131200000L));
    }

    @Test
    public void testGetProfileSince() throws Exception {
        Date profileSince = profile.getProfileSince();
        System.out.println("ProfileSince:" + profileSince);
        assertEquals(profileSince, new Date(1418823120000L));
    }

    @Test
    public void testGetProfileUpdated() throws Exception {
        Date profileUpdated = profile.getProfileUpdated();
        System.out.println("ProfileUpdated:" + profileUpdated);
        assertNotEquals(profileUpdated, new Date(0));
    }

    @Test
    public void testGetApiUsed() throws Exception {
        Date apiUsed = profile.getApiUsed();
        System.out.println("Last API Used:" + apiUsed);
        assertNotEquals(apiUsed, new Date(0));
    }

    @Test
    public void testGetNextApiUse() throws Exception {
        Date nextApiUse = profile.getNextApiUse();
        System.out.println("Next API Use:" + nextApiUse);
        assertNotEquals(nextApiUse, new Date(0));
    }
}