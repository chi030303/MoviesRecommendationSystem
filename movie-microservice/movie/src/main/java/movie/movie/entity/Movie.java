package movie.movie.entity;

import java.io.Serializable;

public class Movie implements Serializable {
    private Integer mid;

    private Integer runtime;

    private Integer budget;

    private Long revenue;

    private Double popularity;

    private Integer voteCount;

    private Double voteAverage;

    private String posterPath;

    private String zhTitle;

    private String zhGenres;

    private String title;

    private String originalTitle;

    private String genres;

    private String originalLanguage;

    private String spokenLanguages;

    private String overview;

    private String releaseDate;

    private String productionCompanies;

    private String productionCountries;

    private String status;

    private String director;

    private String actor;

    private String character;

    private static final long serialVersionUID = 1L;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getZhTitle() {
        return zhTitle;
    }

    public void setZhTitle(String zhTitle) {
        this.zhTitle = zhTitle;
    }

    public String getZhGenres() {
        return zhGenres;
    }

    public void setZhGenres(String zhGenres) {
        this.zhGenres = zhGenres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(String spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(String productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(String productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mid=").append(mid);
        sb.append(", runtime=").append(runtime);
        sb.append(", budget=").append(budget);
        sb.append(", revenue=").append(revenue);
        sb.append(", popularity=").append(popularity);
        sb.append(", voteCount=").append(voteCount);
        sb.append(", voteAverage=").append(voteAverage);
        sb.append(", posterPath=").append(posterPath);
        sb.append(", zhTitle=").append(zhTitle);
        sb.append(", zhGenres=").append(zhGenres);
        sb.append(", title=").append(title);
        sb.append(", originalTitle=").append(originalTitle);
        sb.append(", genres=").append(genres);
        sb.append(", originalLanguage=").append(originalLanguage);
        sb.append(", spokenLanguages=").append(spokenLanguages);
        sb.append(", overview=").append(overview);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", productionCompanies=").append(productionCompanies);
        sb.append(", productionCountries=").append(productionCountries);
        sb.append(", status=").append(status);
        sb.append(", director=").append(director);
        sb.append(", actor=").append(actor);
        sb.append(", character=").append(character);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}