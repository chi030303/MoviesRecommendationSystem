package movie.user.entity;

import java.util.ArrayList;
import java.util.List;

public class UserEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserEntityExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintIsNull() {
            addCriterion("EMAIL_CONSTRAINT is null");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintIsNotNull() {
            addCriterion("EMAIL_CONSTRAINT is not null");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintEqualTo(String value) {
            addCriterion("EMAIL_CONSTRAINT =", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintNotEqualTo(String value) {
            addCriterion("EMAIL_CONSTRAINT <>", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintGreaterThan(String value) {
            addCriterion("EMAIL_CONSTRAINT >", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_CONSTRAINT >=", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintLessThan(String value) {
            addCriterion("EMAIL_CONSTRAINT <", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintLessThanOrEqualTo(String value) {
            addCriterion("EMAIL_CONSTRAINT <=", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintLike(String value) {
            addCriterion("EMAIL_CONSTRAINT like", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintNotLike(String value) {
            addCriterion("EMAIL_CONSTRAINT not like", value, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintIn(List<String> values) {
            addCriterion("EMAIL_CONSTRAINT in", values, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintNotIn(List<String> values) {
            addCriterion("EMAIL_CONSTRAINT not in", values, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintBetween(String value1, String value2) {
            addCriterion("EMAIL_CONSTRAINT between", value1, value2, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailConstraintNotBetween(String value1, String value2) {
            addCriterion("EMAIL_CONSTRAINT not between", value1, value2, "emailConstraint");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedIsNull() {
            addCriterion("EMAIL_VERIFIED is null");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedIsNotNull() {
            addCriterion("EMAIL_VERIFIED is not null");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedEqualTo(Byte value) {
            addCriterion("EMAIL_VERIFIED =", value, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedNotEqualTo(Byte value) {
            addCriterion("EMAIL_VERIFIED <>", value, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedGreaterThan(Byte value) {
            addCriterion("EMAIL_VERIFIED >", value, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedGreaterThanOrEqualTo(Byte value) {
            addCriterion("EMAIL_VERIFIED >=", value, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedLessThan(Byte value) {
            addCriterion("EMAIL_VERIFIED <", value, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedLessThanOrEqualTo(Byte value) {
            addCriterion("EMAIL_VERIFIED <=", value, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedIn(List<Byte> values) {
            addCriterion("EMAIL_VERIFIED in", values, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedNotIn(List<Byte> values) {
            addCriterion("EMAIL_VERIFIED not in", values, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedBetween(Byte value1, Byte value2) {
            addCriterion("EMAIL_VERIFIED between", value1, value2, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedNotBetween(Byte value1, Byte value2) {
            addCriterion("EMAIL_VERIFIED not between", value1, value2, "emailVerified");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("ENABLED is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("ENABLED is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Byte value) {
            addCriterion("ENABLED =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Byte value) {
            addCriterion("ENABLED <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Byte value) {
            addCriterion("ENABLED >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Byte value) {
            addCriterion("ENABLED >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Byte value) {
            addCriterion("ENABLED <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Byte value) {
            addCriterion("ENABLED <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Byte> values) {
            addCriterion("ENABLED in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Byte> values) {
            addCriterion("ENABLED not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Byte value1, Byte value2) {
            addCriterion("ENABLED between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Byte value1, Byte value2) {
            addCriterion("ENABLED not between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andFederationLinkIsNull() {
            addCriterion("FEDERATION_LINK is null");
            return (Criteria) this;
        }

        public Criteria andFederationLinkIsNotNull() {
            addCriterion("FEDERATION_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andFederationLinkEqualTo(String value) {
            addCriterion("FEDERATION_LINK =", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkNotEqualTo(String value) {
            addCriterion("FEDERATION_LINK <>", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkGreaterThan(String value) {
            addCriterion("FEDERATION_LINK >", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkGreaterThanOrEqualTo(String value) {
            addCriterion("FEDERATION_LINK >=", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkLessThan(String value) {
            addCriterion("FEDERATION_LINK <", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkLessThanOrEqualTo(String value) {
            addCriterion("FEDERATION_LINK <=", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkLike(String value) {
            addCriterion("FEDERATION_LINK like", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkNotLike(String value) {
            addCriterion("FEDERATION_LINK not like", value, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkIn(List<String> values) {
            addCriterion("FEDERATION_LINK in", values, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkNotIn(List<String> values) {
            addCriterion("FEDERATION_LINK not in", values, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkBetween(String value1, String value2) {
            addCriterion("FEDERATION_LINK between", value1, value2, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFederationLinkNotBetween(String value1, String value2) {
            addCriterion("FEDERATION_LINK not between", value1, value2, "federationLink");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNull() {
            addCriterion("FIRST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNotNull() {
            addCriterion("FIRST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstNameEqualTo(String value) {
            addCriterion("FIRST_NAME =", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotEqualTo(String value) {
            addCriterion("FIRST_NAME <>", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThan(String value) {
            addCriterion("FIRST_NAME >", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_NAME >=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThan(String value) {
            addCriterion("FIRST_NAME <", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThanOrEqualTo(String value) {
            addCriterion("FIRST_NAME <=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLike(String value) {
            addCriterion("FIRST_NAME like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotLike(String value) {
            addCriterion("FIRST_NAME not like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameIn(List<String> values) {
            addCriterion("FIRST_NAME in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotIn(List<String> values) {
            addCriterion("FIRST_NAME not in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameBetween(String value1, String value2) {
            addCriterion("FIRST_NAME between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotBetween(String value1, String value2) {
            addCriterion("FIRST_NAME not between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNull() {
            addCriterion("LAST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNotNull() {
            addCriterion("LAST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLastNameEqualTo(String value) {
            addCriterion("LAST_NAME =", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotEqualTo(String value) {
            addCriterion("LAST_NAME <>", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThan(String value) {
            addCriterion("LAST_NAME >", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_NAME >=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThan(String value) {
            addCriterion("LAST_NAME <", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThanOrEqualTo(String value) {
            addCriterion("LAST_NAME <=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLike(String value) {
            addCriterion("LAST_NAME like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotLike(String value) {
            addCriterion("LAST_NAME not like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameIn(List<String> values) {
            addCriterion("LAST_NAME in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotIn(List<String> values) {
            addCriterion("LAST_NAME not in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameBetween(String value1, String value2) {
            addCriterion("LAST_NAME between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotBetween(String value1, String value2) {
            addCriterion("LAST_NAME not between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andRealmIdIsNull() {
            addCriterion("REALM_ID is null");
            return (Criteria) this;
        }

        public Criteria andRealmIdIsNotNull() {
            addCriterion("REALM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRealmIdEqualTo(String value) {
            addCriterion("REALM_ID =", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdNotEqualTo(String value) {
            addCriterion("REALM_ID <>", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdGreaterThan(String value) {
            addCriterion("REALM_ID >", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdGreaterThanOrEqualTo(String value) {
            addCriterion("REALM_ID >=", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdLessThan(String value) {
            addCriterion("REALM_ID <", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdLessThanOrEqualTo(String value) {
            addCriterion("REALM_ID <=", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdLike(String value) {
            addCriterion("REALM_ID like", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdNotLike(String value) {
            addCriterion("REALM_ID not like", value, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdIn(List<String> values) {
            addCriterion("REALM_ID in", values, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdNotIn(List<String> values) {
            addCriterion("REALM_ID not in", values, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdBetween(String value1, String value2) {
            addCriterion("REALM_ID between", value1, value2, "realmId");
            return (Criteria) this;
        }

        public Criteria andRealmIdNotBetween(String value1, String value2) {
            addCriterion("REALM_ID not between", value1, value2, "realmId");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampIsNull() {
            addCriterion("CREATED_TIMESTAMP is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampIsNotNull() {
            addCriterion("CREATED_TIMESTAMP is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampEqualTo(Long value) {
            addCriterion("CREATED_TIMESTAMP =", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampNotEqualTo(Long value) {
            addCriterion("CREATED_TIMESTAMP <>", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampGreaterThan(Long value) {
            addCriterion("CREATED_TIMESTAMP >", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATED_TIMESTAMP >=", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampLessThan(Long value) {
            addCriterion("CREATED_TIMESTAMP <", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampLessThanOrEqualTo(Long value) {
            addCriterion("CREATED_TIMESTAMP <=", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampIn(List<Long> values) {
            addCriterion("CREATED_TIMESTAMP in", values, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampNotIn(List<Long> values) {
            addCriterion("CREATED_TIMESTAMP not in", values, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampBetween(Long value1, Long value2) {
            addCriterion("CREATED_TIMESTAMP between", value1, value2, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampNotBetween(Long value1, Long value2) {
            addCriterion("CREATED_TIMESTAMP not between", value1, value2, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkIsNull() {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK is null");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkIsNotNull() {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkEqualTo(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK =", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkNotEqualTo(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK <>", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkGreaterThan(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK >", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK >=", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkLessThan(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK <", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK <=", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkLike(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK like", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkNotLike(String value) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK not like", value, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkIn(List<String> values) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK in", values, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkNotIn(List<String> values) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK not in", values, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkBetween(String value1, String value2) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK between", value1, value2, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andServiceAccountClientLinkNotBetween(String value1, String value2) {
            addCriterion("SERVICE_ACCOUNT_CLIENT_LINK not between", value1, value2, "serviceAccountClientLink");
            return (Criteria) this;
        }

        public Criteria andNotBeforeIsNull() {
            addCriterion("NOT_BEFORE is null");
            return (Criteria) this;
        }

        public Criteria andNotBeforeIsNotNull() {
            addCriterion("NOT_BEFORE is not null");
            return (Criteria) this;
        }

        public Criteria andNotBeforeEqualTo(Integer value) {
            addCriterion("NOT_BEFORE =", value, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeNotEqualTo(Integer value) {
            addCriterion("NOT_BEFORE <>", value, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeGreaterThan(Integer value) {
            addCriterion("NOT_BEFORE >", value, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeGreaterThanOrEqualTo(Integer value) {
            addCriterion("NOT_BEFORE >=", value, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeLessThan(Integer value) {
            addCriterion("NOT_BEFORE <", value, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeLessThanOrEqualTo(Integer value) {
            addCriterion("NOT_BEFORE <=", value, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeIn(List<Integer> values) {
            addCriterion("NOT_BEFORE in", values, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeNotIn(List<Integer> values) {
            addCriterion("NOT_BEFORE not in", values, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeBetween(Integer value1, Integer value2) {
            addCriterion("NOT_BEFORE between", value1, value2, "notBefore");
            return (Criteria) this;
        }

        public Criteria andNotBeforeNotBetween(Integer value1, Integer value2) {
            addCriterion("NOT_BEFORE not between", value1, value2, "notBefore");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}