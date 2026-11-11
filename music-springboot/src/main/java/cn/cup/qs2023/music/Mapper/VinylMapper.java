package cn.cup.qs2023.music.Mapper;

import cn.cup.qs2023.music.domain.Vinyl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface VinylMapper extends BaseMapper<Vinyl> {
    @Select("SELECT * FROM mo_vinyl WHERE MATCH(artist, title) AGAINST(#{keyword} IN NATURAL LANGUAGE MODE) AND delete_flag=0 AND status='listed'")
    List<Vinyl> fulltextSearch(@Param("keyword") String keyword);
}