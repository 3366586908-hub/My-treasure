package cn.cup.qs2023.music.Service.Impl;

import cn.cup.qs2023.music.Mapper.UserMapper;
import cn.cup.qs2023.music.Mapper.VinylMapper;
import cn.cup.qs2023.music.Service.VinylService;
import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.domain.Vinyl;
import cn.cup.qs2023.music.domain.enums.VinylStatus;
import cn.cup.qs2023.music.dto.request.CreateVinylRequest;
import cn.cup.qs2023.music.dto.request.UpdateVinylRequest;
import cn.cup.qs2023.music.dto.request.VinylDetailResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class VinylServiceImpl implements VinylService {
    private final VinylMapper vinylMapper;
    private final UserMapper userMapper;

    @Override
    public IPage<Vinyl> searchVinyls(String keyword, String genre, int page, int size) {
        LambdaQueryWrapper<Vinyl> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Vinyl::getDeleteFlag, 0)
                .eq(Vinyl::getStatus, VinylStatus.LISTED);
        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(Vinyl::getArtist, keyword).or().like(Vinyl::getTitle, keyword));
        }
        if (StringUtils.hasText(genre) && !"all".equalsIgnoreCase(genre)) {
            wrapper.eq(Vinyl::getGenre, genre);
        }
        wrapper.orderByDesc(Vinyl::getCreateAt);
        return vinylMapper.selectPage(new Page<>(page, size), wrapper);
    }

    @Override
    public VinylDetailResponse getVinylDetail(Long vinylId) {
        Vinyl vinyl = vinylMapper.selectById(vinylId);
        if (vinyl == null || vinyl.getDeleteFlag() == 1) {
            throw new RuntimeException("dont exist");
        }
        User seller = userMapper.selectById(vinyl.getSellerId());
        VinylDetailResponse response = new VinylDetailResponse();
        response.setId(vinyl.getId());
        response.setArtist(vinyl.getArtist());
        response.setTitle(vinyl.getTitle());
        response.setReleaseDate(vinyl.getReleaseDate());
        response.setPrice(vinyl.getPrice());
        response.setGenre(vinyl.getGenre());
        response.setCoverImagePath(vinyl.getCoverImagePath());
        response.setDescription(vinyl.getDescription());
        response.setStatus(vinyl.getStatus().getValue());
        if (seller != null) {
            VinylDetailResponse.SellerInfo sellerInfo = new VinylDetailResponse.SellerInfo();
            sellerInfo.setId(seller.getId());
            sellerInfo.setUsername(seller.getUsername());
            response.setSeller(sellerInfo);
        }
        return response;
    }

    @Override
    @Transactional
    public Vinyl createVinyl(Long sellerId, CreateVinylRequest request) {
        Vinyl vinyl = new Vinyl();
        vinyl.setSellerId(sellerId);
        vinyl.setArtist(request.getArtist());
        vinyl.setTitle(request.getTitle());
        vinyl.setReleaseDate(request.getReleaseDate());
        vinyl.setPrice(request.getPrice());
        vinyl.setGenre(request.getGenre());
        vinyl.setDescription(request.getDescription());
        vinyl.setStatus(VinylStatus.valueOf(request.getStatus().toUpperCase()));
        vinylMapper.insert(vinyl);
        return vinyl;
    }

    @Override
    @Transactional
    public Vinyl updateVinyl(Long vinylId, Long sellerId, UpdateVinylRequest request) {
        Vinyl vinyl = vinylMapper.selectById(vinylId);
        if (vinyl == null || vinyl.getDeleteFlag() == 1) {
            throw new RuntimeException("doesnt exist");
        }
        if (!vinyl.getSellerId().equals(sellerId)) {
            throw new RuntimeException("No permission");
        }
        if (StringUtils.hasText(request.getArtist())) vinyl.setArtist(request.getArtist());
        if (StringUtils.hasText(request.getTitle())) vinyl.setTitle(request.getTitle());
        if (request.getReleaseDate() != null) vinyl.setReleaseDate(request.getReleaseDate());
        if (request.getPrice() != null) vinyl.setPrice(request.getPrice());
        if (StringUtils.hasText(request.getGenre())) vinyl.setGenre(request.getGenre());
        if (StringUtils.hasText(request.getDescription())) vinyl.setDescription(request.getDescription());
        if (StringUtils.hasText(request.getStatus())) {
            vinyl.setStatus(VinylStatus.valueOf(request.getStatus().toUpperCase()));
        }
        vinylMapper.updateById(vinyl);
        return vinyl;
    }

    @Override
    @Transactional
    public void deleteVinyl(Long vinylId, Long sellerId) {
        Vinyl vinyl = vinylMapper.selectById(vinylId);
        if (vinyl == null) return;
        if (!vinyl.getSellerId().equals(sellerId)) {
            throw new RuntimeException("No permission delete");
        }
        vinyl.setDeleteFlag(1);
        vinylMapper.updateById(vinyl);
    }

    @Override
    @Transactional
    public void changeVinylStatus(Long vinylId, Long sellerId, String newStatus) {
        Vinyl vinyl = vinylMapper.selectById(vinylId);
        if (vinyl == null || vinyl.getDeleteFlag() == 1) {
            throw new RuntimeException("doesnt exist");
        }
        if (!vinyl.getSellerId().equals(sellerId)) {
            throw new RuntimeException("No permission change");
        }
        vinyl.setStatus(VinylStatus.valueOf(newStatus.toUpperCase()));
        vinylMapper.updateById(vinyl);
    }

    @Override
    public IPage<Vinyl> getMyListings(Long sellerId, int page, int size) {
        LambdaQueryWrapper<Vinyl> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Vinyl::getSellerId, sellerId)
                .eq(Vinyl::getDeleteFlag, 0)
                .orderByDesc(Vinyl::getCreateAt);
        return vinylMapper.selectPage(new Page<>(page, size), wrapper);
    }
}
