package com.example.azure.pojo;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ApiKeys
{
    private String serviceId;
    private List<String> routeId;
}
