package apap.ti.silogistik2106653546.dto;

import apap.ti.silogistik2106653546.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106653546.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106653546.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106653546.model.Gudang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GudangMapper {

    // Define a default instance of the mapper
    GudangMapper INSTANCE = Mappers.getMapper(GudangMapper.class);

    // Define the mapping between CreateGudangRequestDTO and Gudang
    @Mapping(source = "namaGudang", target = "namaGudang")
    @Mapping(source = "alamatGudang", target = "alamatGudang")
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);


    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);

    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);

    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTOToGudang);
}
