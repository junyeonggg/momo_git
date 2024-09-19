package com.MoMo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class CityDto {
	private String zip_code;
	private String si_do;
	private String si_gun_gu;
	private String load_name;
	private String dong_h;
	private String dong_b;
	public CityDto reCity(CityDto city) {
		city.setDong_b(city.getDong_b().replace("-", ""));
		city.setDong_h(city.getDong_h().replace("-", ""));
		city.setLoad_name(city.getLoad_name().replace("-", ""));
		city.setSi_do(city.getSi_do().replace("-", ""));
		city.setSi_gun_gu(city.getSi_gun_gu().replace("-", ""));
		city.setZip_code(city.getZip_code().replace("-", ""));
		return city;
	}
}
