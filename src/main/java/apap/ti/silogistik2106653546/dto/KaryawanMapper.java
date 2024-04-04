package apap.ti.silogistik2106653546.dto;

import org.mapstruct.Mapper;


import apap.ti.silogistik2106653546.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106653546.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    
   Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
