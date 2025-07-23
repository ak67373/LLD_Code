package model;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private String groupId;
    private String name;
    private Set<String> memberIds;

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.memberIds = new HashSet<>();
    }

    public void addMember(User user) {
        memberIds.add(user.getId());
    }

    public boolean containsUser(String userId) {
        return memberIds.contains(userId);
    }

    public Set<String> getMembers() {
        return memberIds;
    }

    public String getGroupId() {
        return groupId;
    }
}
