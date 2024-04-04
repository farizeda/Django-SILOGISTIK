package apap.ti.silogistik2106653546.dto;

import apap.ti.silogistik2106653546.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653546.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653546.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106653546.model.Barang;

import org.hibernate.persister.entity.mutation.UpdateCoordinator;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    @Mapping(source = "totalStok", target = "totalStok")
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang, int totalStok);

}
