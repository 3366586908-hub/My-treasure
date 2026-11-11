package cn.cup.qs2023.music.Service;

import cn.cup.qs2023.music.domain.Vinyl;
import cn.cup.qs2023.music.dto.request.CreateVinylRequest;
import cn.cup.qs2023.music.dto.request.UpdateVinylRequest;
import cn.cup.qs2023.music.dto.request.VinylDetailResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface VinylService {
    IPage<Vinyl> searchVinyls(String keyword, String genre, int page, int size);
    VinylDetailResponse getVinylDetail(Long vinylId);
    Vinyl createVinyl(Long sellerId, CreateVinylRequest request);
    Vinyl updateVinyl(Long vinylId, Long sellerId, UpdateVinylRequest request);
    void deleteVinyl(Long vinylId, Long sellerId);
    void changeVinylStatus(Long vinylId, Long sellerId, String newStatus);
    IPage<Vinyl> getMyListings(Long sellerId, int page, int size);
}
