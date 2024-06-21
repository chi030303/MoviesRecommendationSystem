package movie.user.entity;

import java.io.Serializable;

public class UserAttribute implements Serializable {
    private String id;

    private String name;

    private String value;

    private String userId;

    private byte[] longValueHash;

    private byte[] longValueHashLowerCase;

    private String longValue;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public byte[] getLongValueHash() {
        return longValueHash;
    }

    public void setLongValueHash(byte[] longValueHash) {
        this.longValueHash = longValueHash;
    }

    public byte[] getLongValueHashLowerCase() {
        return longValueHashLowerCase;
    }

    public void setLongValueHashLowerCase(byte[] longValueHashLowerCase) {
        this.longValueHashLowerCase = longValueHashLowerCase;
    }

    public String getLongValue() {
        return longValue;
    }

    public void setLongValue(String longValue) {
        this.longValue = longValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", userId=").append(userId);
        sb.append(", longValueHash=").append(longValueHash);
        sb.append(", longValueHashLowerCase=").append(longValueHashLowerCase);
        sb.append(", longValue=").append(longValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}