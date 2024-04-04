package apap.ti.silogistik2106653546.dto;


import apap.ti.silogistik2106653546.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653546.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106653546.dto.response.ReadPermintaanPengirimanDTO;
import apap.ti.silogistik2106653546.model.Barang;
import apap.ti.silogistik2106653546.model.PermintaanPengiriman;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {

    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman (CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    ReadPermintaanPengirimanDTO permintaanPengirimanToReadPermintaanPengirimanDTO(PermintaanPengiriman permintaanPengiriman);
}
