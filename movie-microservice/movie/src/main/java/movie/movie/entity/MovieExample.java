package movie.movie.entity;

import java.util.ArrayList;
import java.util.List;

public class MovieExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieExample() {
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

        public Criteria andMidIsNull() {
            addCriterion("mid is null");
            return (Criteria) this;
        }

        public Criteria andMidIsNotNull() {
            addCriterion("mid is not null");
            return (Criteria) this;
        }

        public Criteria andMidEqualTo(Integer value) {
            addCriterion("mid =", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotEqualTo(Integer value) {
            addCriterion("mid <>", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThan(Integer value) {
            addCriterion("mid >", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mid >=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThan(Integer value) {
            addCriterion("mid <", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThanOrEqualTo(Integer value) {
            addCriterion("mid <=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidIn(List<Integer> values) {
            addCriterion("mid in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotIn(List<Integer> values) {
            addCriterion("mid not in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidBetween(Integer value1, Integer value2) {
            addCriterion("mid between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotBetween(Integer value1, Integer value2) {
            addCriterion("mid not between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andRuntimeIsNull() {
            addCriterion("runtime is null");
            return (Criteria) this;
        }

        public Criteria andRuntimeIsNotNull() {
            addCriterion("runtime is not null");
            return (Criteria) this;
        }

        public Criteria andRuntimeEqualTo(Integer value) {
            addCriterion("runtime =", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeNotEqualTo(Integer value) {
            addCriterion("runtime <>", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeGreaterThan(Integer value) {
            addCriterion("runtime >", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("runtime >=", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeLessThan(Integer value) {
            addCriterion("runtime <", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeLessThanOrEqualTo(Integer value) {
            addCriterion("runtime <=", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeIn(List<Integer> values) {
            addCriterion("runtime in", values, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeNotIn(List<Integer> values) {
            addCriterion("runtime not in", values, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeBetween(Integer value1, Integer value2) {
            addCriterion("runtime between", value1, value2, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeNotBetween(Integer value1, Integer value2) {
            addCriterion("runtime not between", value1, value2, "runtime");
            return (Criteria) this;
        }

        public Criteria andBudgetIsNull() {
            addCriterion("budget is null");
            return (Criteria) this;
        }

        public Criteria andBudgetIsNotNull() {
            addCriterion("budget is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetEqualTo(Integer value) {
            addCriterion("budget =", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotEqualTo(Integer value) {
            addCriterion("budget <>", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetGreaterThan(Integer value) {
            addCriterion("budget >", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetGreaterThanOrEqualTo(Integer value) {
            addCriterion("budget >=", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetLessThan(Integer value) {
            addCriterion("budget <", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetLessThanOrEqualTo(Integer value) {
            addCriterion("budget <=", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetIn(List<Integer> values) {
            addCriterion("budget in", values, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotIn(List<Integer> values) {
            addCriterion("budget not in", values, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetBetween(Integer value1, Integer value2) {
            addCriterion("budget between", value1, value2, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotBetween(Integer value1, Integer value2) {
            addCriterion("budget not between", value1, value2, "budget");
            return (Criteria) this;
        }

        public Criteria andRevenueIsNull() {
            addCriterion("revenue is null");
            return (Criteria) this;
        }

        public Criteria andRevenueIsNotNull() {
            addCriterion("revenue is not null");
            return (Criteria) this;
        }

        public Criteria andRevenueEqualTo(Long value) {
            addCriterion("revenue =", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotEqualTo(Long value) {
            addCriterion("revenue <>", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueGreaterThan(Long value) {
            addCriterion("revenue >", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueGreaterThanOrEqualTo(Long value) {
            addCriterion("revenue >=", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueLessThan(Long value) {
            addCriterion("revenue <", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueLessThanOrEqualTo(Long value) {
            addCriterion("revenue <=", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueIn(List<Long> values) {
            addCriterion("revenue in", values, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotIn(List<Long> values) {
            addCriterion("revenue not in", values, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueBetween(Long value1, Long value2) {
            addCriterion("revenue between", value1, value2, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotBetween(Long value1, Long value2) {
            addCriterion("revenue not between", value1, value2, "revenue");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNull() {
            addCriterion("popularity is null");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNotNull() {
            addCriterion("popularity is not null");
            return (Criteria) this;
        }

        public Criteria andPopularityEqualTo(Double value) {
            addCriterion("popularity =", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotEqualTo(Double value) {
            addCriterion("popularity <>", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThan(Double value) {
            addCriterion("popularity >", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThanOrEqualTo(Double value) {
            addCriterion("popularity >=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThan(Double value) {
            addCriterion("popularity <", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThanOrEqualTo(Double value) {
            addCriterion("popularity <=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityIn(List<Double> values) {
            addCriterion("popularity in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotIn(List<Double> values) {
            addCriterion("popularity not in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityBetween(Double value1, Double value2) {
            addCriterion("popularity between", value1, value2, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotBetween(Double value1, Double value2) {
            addCriterion("popularity not between", value1, value2, "popularity");
            return (Criteria) this;
        }

        public Criteria andVoteCountIsNull() {
            addCriterion("vote_count is null");
            return (Criteria) this;
        }

        public Criteria andVoteCountIsNotNull() {
            addCriterion("vote_count is not null");
            return (Criteria) this;
        }

        public Criteria andVoteCountEqualTo(Integer value) {
            addCriterion("vote_count =", value, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountNotEqualTo(Integer value) {
            addCriterion("vote_count <>", value, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountGreaterThan(Integer value) {
            addCriterion("vote_count >", value, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote_count >=", value, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountLessThan(Integer value) {
            addCriterion("vote_count <", value, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountLessThanOrEqualTo(Integer value) {
            addCriterion("vote_count <=", value, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountIn(List<Integer> values) {
            addCriterion("vote_count in", values, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountNotIn(List<Integer> values) {
            addCriterion("vote_count not in", values, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountBetween(Integer value1, Integer value2) {
            addCriterion("vote_count between", value1, value2, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteCountNotBetween(Integer value1, Integer value2) {
            addCriterion("vote_count not between", value1, value2, "voteCount");
            return (Criteria) this;
        }

        public Criteria andVoteAverageIsNull() {
            addCriterion("vote_average is null");
            return (Criteria) this;
        }

        public Criteria andVoteAverageIsNotNull() {
            addCriterion("vote_average is not null");
            return (Criteria) this;
        }

        public Criteria andVoteAverageEqualTo(Double value) {
            addCriterion("vote_average =", value, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageNotEqualTo(Double value) {
            addCriterion("vote_average <>", value, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageGreaterThan(Double value) {
            addCriterion("vote_average >", value, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageGreaterThanOrEqualTo(Double value) {
            addCriterion("vote_average >=", value, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageLessThan(Double value) {
            addCriterion("vote_average <", value, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageLessThanOrEqualTo(Double value) {
            addCriterion("vote_average <=", value, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageIn(List<Double> values) {
            addCriterion("vote_average in", values, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageNotIn(List<Double> values) {
            addCriterion("vote_average not in", values, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageBetween(Double value1, Double value2) {
            addCriterion("vote_average between", value1, value2, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andVoteAverageNotBetween(Double value1, Double value2) {
            addCriterion("vote_average not between", value1, value2, "voteAverage");
            return (Criteria) this;
        }

        public Criteria andPosterPathIsNull() {
            addCriterion("poster_path is null");
            return (Criteria) this;
        }

        public Criteria andPosterPathIsNotNull() {
            addCriterion("poster_path is not null");
            return (Criteria) this;
        }

        public Criteria andPosterPathEqualTo(String value) {
            addCriterion("poster_path =", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathNotEqualTo(String value) {
            addCriterion("poster_path <>", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathGreaterThan(String value) {
            addCriterion("poster_path >", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathGreaterThanOrEqualTo(String value) {
            addCriterion("poster_path >=", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathLessThan(String value) {
            addCriterion("poster_path <", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathLessThanOrEqualTo(String value) {
            addCriterion("poster_path <=", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathLike(String value) {
            addCriterion("poster_path like", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathNotLike(String value) {
            addCriterion("poster_path not like", value, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathIn(List<String> values) {
            addCriterion("poster_path in", values, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathNotIn(List<String> values) {
            addCriterion("poster_path not in", values, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathBetween(String value1, String value2) {
            addCriterion("poster_path between", value1, value2, "posterPath");
            return (Criteria) this;
        }

        public Criteria andPosterPathNotBetween(String value1, String value2) {
            addCriterion("poster_path not between", value1, value2, "posterPath");
            return (Criteria) this;
        }

        public Criteria andZhTitleIsNull() {
            addCriterion("zh_title is null");
            return (Criteria) this;
        }

        public Criteria andZhTitleIsNotNull() {
            addCriterion("zh_title is not null");
            return (Criteria) this;
        }

        public Criteria andZhTitleEqualTo(String value) {
            addCriterion("zh_title =", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleNotEqualTo(String value) {
            addCriterion("zh_title <>", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleGreaterThan(String value) {
            addCriterion("zh_title >", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleGreaterThanOrEqualTo(String value) {
            addCriterion("zh_title >=", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleLessThan(String value) {
            addCriterion("zh_title <", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleLessThanOrEqualTo(String value) {
            addCriterion("zh_title <=", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleLike(String value) {
            addCriterion("zh_title like", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleNotLike(String value) {
            addCriterion("zh_title not like", value, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleIn(List<String> values) {
            addCriterion("zh_title in", values, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleNotIn(List<String> values) {
            addCriterion("zh_title not in", values, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleBetween(String value1, String value2) {
            addCriterion("zh_title between", value1, value2, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhTitleNotBetween(String value1, String value2) {
            addCriterion("zh_title not between", value1, value2, "zhTitle");
            return (Criteria) this;
        }

        public Criteria andZhGenresIsNull() {
            addCriterion("zh_genres is null");
            return (Criteria) this;
        }

        public Criteria andZhGenresIsNotNull() {
            addCriterion("zh_genres is not null");
            return (Criteria) this;
        }

        public Criteria andZhGenresEqualTo(String value) {
            addCriterion("zh_genres =", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresNotEqualTo(String value) {
            addCriterion("zh_genres <>", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresGreaterThan(String value) {
            addCriterion("zh_genres >", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresGreaterThanOrEqualTo(String value) {
            addCriterion("zh_genres >=", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresLessThan(String value) {
            addCriterion("zh_genres <", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresLessThanOrEqualTo(String value) {
            addCriterion("zh_genres <=", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresLike(String value) {
            addCriterion("zh_genres like", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresNotLike(String value) {
            addCriterion("zh_genres not like", value, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresIn(List<String> values) {
            addCriterion("zh_genres in", values, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresNotIn(List<String> values) {
            addCriterion("zh_genres not in", values, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresBetween(String value1, String value2) {
            addCriterion("zh_genres between", value1, value2, "zhGenres");
            return (Criteria) this;
        }

        public Criteria andZhGenresNotBetween(String value1, String value2) {
            addCriterion("zh_genres not between", value1, value2, "zhGenres");
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