package cn.mrcsh.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CurrentServer {

    private int id;
    @JsonProperty("enforcesSecureChat")
    private Boolean enforcesSecureChat;
    @JsonProperty("description")
    private DescriptionDTO description;
    @JsonProperty("players")
    private PlayersDTO players;
    @JsonProperty("version")
    private VersionDTO version;
    @JsonProperty("favicon")
    private String favicon;
    private Boolean isOnline;
    private String ip;
    @NoArgsConstructor
    @Data
    public static class DescriptionDTO {
        @JsonProperty("extra")
        private List<ExtraDTO> extra;
        @JsonProperty("text")
        private String text;

        @NoArgsConstructor
        @Data
        public static class ExtraDTO {
            @JsonProperty("text")
            private String text;
        }
    }

    @NoArgsConstructor
    @Data
    public static class PlayersDTO {
        @JsonProperty("max")
        private Integer max;
        @JsonProperty("online")
        private Integer online;
        @JsonProperty("sample")
        private List<SampleDTO> sample;

        @NoArgsConstructor
        @Data
        public static class SampleDTO {
            @JsonProperty("id")
            private String id;
            @JsonProperty("name")
            private String name;
        }
    }

    @NoArgsConstructor
    @Data
    public static class VersionDTO {
        @JsonProperty("name")
        private String name;
        @JsonProperty("protocol")
        private Integer protocol;
    }
}
