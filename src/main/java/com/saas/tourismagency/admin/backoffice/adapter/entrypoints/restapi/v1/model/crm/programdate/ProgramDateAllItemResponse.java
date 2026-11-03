package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.ProgramResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemResponse;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDateAllItemResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("tour")
    private ProgramResponse program;

    @JsonProperty("tourItems")
    private List<ProgramItemResponse> programItems;

    public static ProgramDateAllItemResponse from(ProgramDateDomain domain, List<ProgramItemDomain> programItemsDomain) {
        ProgramDateAllItemResponse programDateAllItemResponse = new ProgramDateAllItemResponse();
        programDateAllItemResponse.setId(domain.getId());
        programDateAllItemResponse.setName(domain.getName());
        programDateAllItemResponse.setDescription(domain.getDescription());
        programDateAllItemResponse.setIdentify(domain.getIdentify());
        programDateAllItemResponse.setDay(domain.getDay());

        if (domain.getProgram() != null &&
                domain.getProgram().getId() != null &&
                !domain.getProgram().getId().isEmpty()){
            programDateAllItemResponse.setProgram(ProgramResponse.from(domain.getProgram()));
        }

        if (domain.getTours() != null && !domain.getTours().isEmpty()){
            List<TourDomain> tourDomainListResult = domain.getTours().stream().collect(Collectors.toList());
            StringBuffer result = new StringBuffer();
            for(TourDomain item: tourDomainListResult){
                result.append(item.getName() + " / ");
            }
            if ( result.length() > 1 ){
                result.delete(result.length()-2, result.length()-1);
            }
            programDateAllItemResponse.setName(programDateAllItemResponse.getDay() + (programDateAllItemResponse.getName() != null && !programDateAllItemResponse.getName().isEmpty() ? " - " : "") + programDateAllItemResponse.getName() + " - " + result);
        }else{
            programDateAllItemResponse.setName(programDateAllItemResponse.getDay() + (programDateAllItemResponse.getName() != null && !programDateAllItemResponse.getName().isEmpty() ? " - " : "") + programDateAllItemResponse.getName());
        }

        programDateAllItemResponse.setProgramItems(
                programItemsDomain.
                        stream().
                        map(programItemDomain -> ProgramItemResponse.from(programItemDomain)).
                        collect(Collectors.toList())
        );
        return programDateAllItemResponse;
    }
}
