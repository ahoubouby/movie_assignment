package com.ahoububy.movies.dto;

import com.ahoububy.movies.models.TypeMovie;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO implements Serializable {
    private static final long serialVersionUID = 0x4387_2908_926c_2c4fL;
    private String title;
    private String director;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date releaseDate;
    private TypeMovie type;
}
