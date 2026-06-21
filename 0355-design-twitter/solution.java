import java.util.*;

class Twitter {

    private static class Tweet {
        int tweetId;
        int time;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    private Map<Integer, Set<Integer>> following;
    private Map<Integer, List<Tweet>> tweets;
    private int timestamp;

    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
        timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new ArrayList<>());
        tweets.get(userId).add(new Tweet(tweetId, timestamp++));
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> maxHeap =
                new PriorityQueue<>((a, b) -> b.time - a.time);

        // User's own tweets
        if (tweets.containsKey(userId)) {
            maxHeap.addAll(tweets.get(userId));
        }

        // Tweets from followees
        Set<Integer> followees =
                following.getOrDefault(userId, new HashSet<>());

        for (int followee : followees) {
            if (tweets.containsKey(followee)) {
                maxHeap.addAll(tweets.get(followee));
            }
        }

        List<Integer> feed = new ArrayList<>();

        for (int i = 0; i < 10 && !maxHeap.isEmpty(); i++) {
            feed.add(maxHeap.poll().tweetId);
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {
        following.computeIfAbsent(followerId, k -> new HashSet<>());
        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
}
